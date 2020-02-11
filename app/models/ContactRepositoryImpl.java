package models;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class ContactRepositoryImpl implements ContactRepository{

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public ContactRepositoryImpl(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }


    @Override
    public CompletionStage<Contact> addContact(Contact contact){
        return supplyAsync(() -> wrap(em -> insertContact(em, contact)), executionContext);
    }

    @Override
    public CompletionStage<Contact> getContact(Long personId) {
        return supplyAsync(() -> wrap(em -> getContact(em, personId)), executionContext);
    }

    @Override
    public CompletionStage<Stream<SocialLink>> getSocialLink(Long contactId, Long personId) {
        return supplyAsync(() -> wrap(em -> getSocialLinks(em, contactId, personId)), executionContext);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Contact insertContact(EntityManager em, Contact contact) {
        em.persist(contact);
        return contact;
    }

    private Contact getContact(EntityManager em, Long personId) {
        Contact contact = em.createQuery("select c from Contact c where c.personId=" + personId, Contact.class).getSingleResult();
        return contact;
    }

    private Stream<SocialLink> getSocialLinks(EntityManager em, Long contactId, Long personId) {
        List<SocialLink> socialLinks = em.createQuery("select s from  SocialLink s where s.contactId="+contactId+" and s.personId="+personId+" order by s.updatedAt",
                SocialLink.class).getResultList();
        return socialLinks.stream();
    }

}
