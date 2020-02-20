package models.repository;

import models.Workinfo;
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
    public CompletionStage<Stream<Workinfo>> addWork(List<Workinfo> workinfos) {
        return supplyAsync(() -> wrap(em -> insertWork(em, workinfos)), executionContext);

    }

    @Override
    public CompletionStage<Stream<Workinfo>> getWorks(Long personId) {
        return supplyAsync(() -> wrap(em -> getWorks(em, personId)), executionContext);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Stream<Workinfo> insertWork(EntityManager em, List<Workinfo> workinfos) {
        workinfos.forEach(em::persist);
        return workinfos.stream();
    }

    private Stream<Workinfo> getWorks(EntityManager em, Long personId) {
        List<Workinfo> workinfos = em.createQuery("select e from  Workinfo e where e.personId="+personId+" order by e.updatedAt",
                Workinfo.class).getResultList();
        return workinfos.stream();
    }
}
