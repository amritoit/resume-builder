package models.repository;

import models.Person;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static play.libs.Json.toJson;

/**
 * Provide JPA operations running inside of a thread pool sized to the connection pool
 */
public class PersonRepositoryImpl implements PersonRepository {

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public PersonRepositoryImpl(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Person> add(Person person) {
        return supplyAsync(() -> wrap(em -> insert(em, person)), executionContext);
    }

    @Override
    public CompletionStage<Person> getPerson(Long personId) {
        return supplyAsync(() -> wrap(em -> getPerson(em, personId)), executionContext);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Person insert(EntityManager em, Person person) {
        System.out.println("person:"+toJson(person));
        em.persist(person);
        return person;
    }

    private Person getPerson(EntityManager em, Long personId) {
        Person person = em.createQuery("select p from Person p where p.id="+personId, Person.class).getSingleResult();
        return person;
    }
}