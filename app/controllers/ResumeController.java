package controllers;

import dao.ResumeDao;
import models.*;
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


    private final HttpExecutionContext ec;

    @Inject
    public ResumeController(PersonRepository personRepository, ContactRepository contactRepository,
                            SkillRepository skillRepository, EducationRepository educationRepository, HttpExecutionContext ec) {
        this.personRepository = personRepository;
        this.contactRepository = contactRepository;
        this.skillRepository = skillRepository;
        this.educationRepository = educationRepository;
        this.ec = ec;
    }

    public CompletionStage<Result>  getPerson(Long personId) {
        CompletionStage<Person> person = personRepository.getPerson(personId);
        CompletionStage<Contact> contact = contactRepository.getContact(personId);
        CompletionStage<Stream<SocialLink>> streamSocialLink = contact.thenCompose(c-> contactRepository.getSocialLink(c.getId(), personId));
        CompletionStage<Stream<Skill>> streamSkills = skillRepository.getSkills(personId);
        CompletionStage<Stream<Education>> streamEducation = educationRepository.getEducation(personId);

        ResumeDao resumeDao = new ResumeDao();
        return person.thenAccept(resumeDao::setBasic).thenRunAsync(() -> contact.thenAccept(resumeDao::setContact)).
                thenRunAsync(()->streamSocialLink.thenAccept(s -> resumeDao.setSocial(s.collect(Collectors.toList())))).
                thenRunAsync(()->streamSkills.thenAccept(s->resumeDao.setSkill(s.collect(Collectors.toList())))).
                thenRunAsync(()->streamEducation.thenAccept(s->resumeDao.setEducation(s.collect(Collectors.toList())))).
                thenApplyAsync(s-> ok(toJson(resumeDao)));
    }

//    public CompletionStage<Result> getContact() {
//        return contactRepository
//                .getContact(personRepository
//                        .list().thenApplyAsync(p->))
//                .thenApplyAsync(personStream -> ok(toJson(personStream.collect(Collectors.toList()))), ec.current());
//    }
}
