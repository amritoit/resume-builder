package models.repository;

import models.Work;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class WorkRepositoryImpl implements WorkRepository{

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public WorkRepositoryImpl(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Work> addWork(Work work) {
        return supplyAsync(() -> wrap(em -> insertWork(em, work)), executionContext);

    }

    @Override
    public CompletionStage<Stream<Work>> getWorks(Long personId) {
        return supplyAsync(() -> wrap(em -> getWorks(em, personId)), executionContext);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Work insertWork(EntityManager em, Work work) {
        em.persist(work);
        return work;
    }

    private Stream<Work> getWorks(EntityManager em, Long personId) {
        List<Work> works = em.createQuery("select e from  Work e where e.personId="+personId+" order by e.updatedAt",
                Work.class).getResultList();
        return works.stream();
    }
}
