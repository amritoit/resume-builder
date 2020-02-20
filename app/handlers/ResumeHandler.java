package handlers;

import dao.ResumeDTO;
import models.*;
import models.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.concurrent.HttpExecutionContext;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TODO: Handling time-outs
public class ResumeHandler {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PersonRepository personRepository;
    private final ContactRepository contactRepository;
    private final SkillRepository skillRepository;
    private final EducationRepository educationRepository;
    private final WorkRepository workRepository;
    private final AdditionalInformationRepository additionalInformationRepository;

    private final HttpExecutionContext ec;

    @Inject
    public ResumeHandler(PersonRepository personRepository, ContactRepository contactRepository,
                         SkillRepository skillRepository, EducationRepository educationRepository,
                         WorkRepository workRepository, AdditionalInformationRepository additionalInformationRepository,
                         play.libs.concurrent.HttpExecutionContext ec) {
        this.personRepository = personRepository;
        this.contactRepository = contactRepository;
        this.skillRepository = skillRepository;
        this.educationRepository = educationRepository;
        this.workRepository = workRepository;
        this.additionalInformationRepository = additionalInformationRepository;
        this.ec = ec;
    }

    public CompletionStage<ResumeDTO> getResumeData(Long personId) {
        CompletionStage<Person> person = personRepository.getPerson(personId);
        CompletionStage<Contact> contact = contactRepository.getContact(personId);
        CompletionStage<Stream<Skill>> streamSkills = skillRepository.getSkills(personId);
        CompletionStage<Stream<Education>> streamEducation = educationRepository.getEducation(personId);
        CompletionStage<Stream<Workinfo>> streamWork = workRepository.getWorks(personId);
        CompletionStage<Stream<AdditionalInformation>> streamAdditionalInfo = additionalInformationRepository.getAdditionalInformations(personId);
        ResumeDTO resumeDTO = new ResumeDTO();

        return person.thenCombineAsync(contact, (p, c) -> {
            resumeDTO.setBasic(p);
            resumeDTO.setContact(c);
            return resumeDTO;
        }).thenCombineAsync(streamSkills.thenApply(s->s), (lResumeDTO, skills)->{
            lResumeDTO.setSkills(skills.collect(Collectors.toList()));
            return lResumeDTO;
        }).thenCombineAsync(streamEducation.thenApply(s->s), (lResumeDTO, educations)->{
            lResumeDTO.setEducation(educations.collect(Collectors.toList()));
            return lResumeDTO;
        }).thenCombineAsync(streamWork.thenApply(s->s), (lResumeDTO, works)->{
            lResumeDTO.setWorkinfos(works.collect(Collectors.toList()));
            return lResumeDTO;
        }).thenCombineAsync(streamAdditionalInfo.thenApply(s->s), (lResumeDTO, additionalInformation)->{
            lResumeDTO.setAdditionalInfo(additionalInformation.collect(Collectors.toList()));
            return lResumeDTO;
        });
    }

    //TODO: contact_id in person table not populated
    public CompletionStage<ResumeDTO> createResume(ResumeDTO resumeDTO) {

        CompletionStage<Person> personCompletionStage = personRepository.add(resumeDTO.getBasic());
        CompletionStage<ResumeDTO> resumeDTOCompletionStage =  personCompletionStage.thenApply(person -> {
            resumeDTO.getContact().setPersonId(person.getId());
            resumeDTO.getContact().getSocialLinks().forEach(s -> s.setPersonId(person.getId()));
            resumeDTO.getSkills().forEach(s -> s.setPersonId(person.getId()));
            resumeDTO.getEducation().forEach(e -> e.setPersonId(person.getId()));
            resumeDTO.getWorkinfos().forEach(w -> w.setPersonId(person.getId()));
            resumeDTO.getAdditionalInfo().forEach(a -> a.setPersonId(person.getId()));
            return resumeDTO;
        });
        CompletionStage<Contact> contactCompletionStage = resumeDTOCompletionStage.thenComposeAsync(lResumeDTO -> {
            lResumeDTO.getContact().getSocialLinks().forEach(s -> s.setContact(lResumeDTO.getContact()));
            return contactRepository.addContact(lResumeDTO.getContact());
        });

        CompletionStage<Stream<Skill>> skillCompletionStage = resumeDTOCompletionStage.thenComposeAsync(
                lResumeDTO -> skillRepository.addSkill(lResumeDTO.getSkills()));

        CompletionStage<Stream<Education>> eduCompletionStage = resumeDTOCompletionStage.thenComposeAsync(
                lResumeDTO -> educationRepository.addEducation(lResumeDTO.getEducation()));

        CompletionStage<Stream<Workinfo>> workCompletionStage = resumeDTOCompletionStage.thenComposeAsync(
                lResumeDTO -> workRepository.addWork(lResumeDTO.getWorkinfos()));

        CompletionStage<Stream<AdditionalInformation>> addInfoCompletionStage = resumeDTOCompletionStage.thenComposeAsync(
                lResumeDTO -> additionalInformationRepository.addAdditionalInformation(lResumeDTO.getAdditionalInfo()));

        CompletionStage<ResumeDTO> contactSkill = contactCompletionStage.thenCombine(skillCompletionStage.thenApply(s->s),
                (contact, skills)-> resumeDTO);

        CompletionStage<ResumeDTO> educationWork = eduCompletionStage.thenCombine(workCompletionStage.thenApply(s->s),
                (contact, skills)-> resumeDTO);

        CompletionStage<ResumeDTO> contactSkillEducationWork = contactSkill.thenCombineAsync(educationWork.thenApply(s->s),(c,s)->s);

        return contactSkillEducationWork.thenCombineAsync(addInfoCompletionStage.thenApply(s->s), (c,s)->c);
    }
}
