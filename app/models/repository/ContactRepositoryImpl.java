package models.repository;

import models.Contact;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

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
    public CompletionStage<Contact> mergeContact(Contact contact) {
        return supplyAsync(() -> wrap(em -> mergeContact(em, contact)), executionContext);
    }

    @Override
    public CompletionStage<Contact> addContact(Contact contact){
        return supplyAsync(() -> wrap(em -> insertContact(em, contact)), executionContext);
    }

    @Override
    public CompletionStage<Contact> getContact(Long personId) {
        return supplyAsync(() -> wrap(em -> getContact(em, personId)), executionContext);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Contact insertContact(EntityManager em, Contact contact) {
        em.persist(contact);
        return contact;
    }

    private Contact mergeContact(EntityManager em, Contact contact) {
        em.merge(contact);
        return contact;
    }

    private Contact getContact(EntityManager em, Long personId) {
        Contact contact = em.createQuery("select c from Contact c where c.personId=" + personId, Contact.class).getSingleResult();
        return contact;
    }
}
