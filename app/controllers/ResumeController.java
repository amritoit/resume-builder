package controllers;

import dao.ResumeDTO;
import models.*;
import models.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static play.libs.Json.toJson;

public class ResumeController extends Controller {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PersonRepository personRepository;
    private final ContactRepository contactRepository;
    private final SkillRepository skillRepository;
    private final EducationRepository educationRepository;
    private final WorkRepository workRepository;
    private final AdditionalInformationRepository additionalInformationRepository;


    private final HttpExecutionContext ec;

    @Inject
    public ResumeController(PersonRepository personRepository, ContactRepository contactRepository,
                            SkillRepository skillRepository, EducationRepository educationRepository,
                            WorkRepository workRepository, AdditionalInformationRepository additionalInformationRepository,
                            HttpExecutionContext ec) {
        this.personRepository = personRepository;
        this.contactRepository = contactRepository;
        this.skillRepository = skillRepository;
        this.educationRepository = educationRepository;
        this.workRepository = workRepository;
        this.additionalInformationRepository = additionalInformationRepository;
        this.ec = ec;
    }

    public CompletionStage<Result>  getPerson(Long personId) {
        CompletionStage<Person> person = personRepository.getPerson(personId);
        CompletionStage<Contact> contact = contactRepository.getContact(personId);
        CompletionStage<Stream<Skill>> streamSkills = skillRepository.getSkills(personId);
        CompletionStage<Stream<Education>> streamEducation = educationRepository.getEducation(personId);
        CompletionStage<Stream<Work>> streamWork = workRepository.getWorks(personId);
        CompletionStage<Stream<AdditionalInformation>> streamAdditionalInfo = additionalInformationRepository.getAdditionalInformations(personId);

        ResumeDTO resumeDTO = new ResumeDTO();
        return person.thenAccept(resumeDTO::setBasic).thenRunAsync(() -> contact.thenAccept(resumeDTO::setContact)).
                thenRunAsync(()->streamSkills.thenAccept(s-> resumeDTO.setSkills(s.collect(Collectors.toList())))).
                thenRunAsync(()->streamEducation.thenAccept(s-> resumeDTO.setEducation(s.collect(Collectors.toList())))).
                thenRunAsync(()->streamWork.thenAccept(s-> resumeDTO.setWorks(s.collect(Collectors.toList())))).
                thenRunAsync(()->streamAdditionalInfo.thenAccept(s-> resumeDTO.setAdditionalInfo(s.collect(Collectors.toList())))).
                thenApplyAsync(s-> ok(toJson(resumeDTO)));
    }

//    public CompletionStage<Result> getContact() {
//        return contactRepository
//                .getContact(personRepository
//                        .list().thenApplyAsync(p->))
//                .thenApplyAsync(personStream -> ok(toJson(personStream.collect(Collectors.toList()))), ec.current());
//    }
}
