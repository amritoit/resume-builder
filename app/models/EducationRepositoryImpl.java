package models;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class EducationRepositoryImpl implements EducationRepository {

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public EducationRepositoryImpl(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Education> addEducation(Education education) {
        return supplyAsync(() -> wrap(em -> insertEducation(em, education)), executionContext);
    }

    @Override
    public CompletionStage<Stream<Education>> getEducation(Long personId) {
        return supplyAsync(() -> wrap(em -> getEducation(em, personId)), executionContext);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Education insertEducation(EntityManager em, Education education) {
        em.persist(education);
        return education;
    }


    private Stream<Education> getEducation(EntityManager em, Long personId) {
        List<Education> educations = em.createQuery("select e from  Education e where e.personId="+personId+" order by e.updatedAt",
                Education.class).getResultList();
        return educations.stream();
    }
}
