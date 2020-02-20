package models.repository;

import models.AdditionalInformation;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class AdditionalInformationRepositoryImpl implements AdditionalInformationRepository{

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public AdditionalInformationRepositoryImpl(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }



    @Override
    public CompletionStage<Stream<AdditionalInformation>> addAdditionalInformation(List<AdditionalInformation> additionalInformation) {
        return supplyAsync(() -> wrap(em -> insertAdditionalInformation(em, additionalInformation)), executionContext);

    }

    @Override
    public CompletionStage<Stream<AdditionalInformation>> getAdditionalInformations(Long personId) {
        return supplyAsync(() -> wrap(em -> getAdditionalInformations(em, personId)), executionContext);

    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Stream<AdditionalInformation> insertAdditionalInformation(EntityManager em, List<AdditionalInformation> additionalInformation) {
        additionalInformation.forEach(em::persist);
        return additionalInformation.stream();
    }

    private Stream<AdditionalInformation> getAdditionalInformations(EntityManager em, Long personId) {
        List<AdditionalInformation> additionalInformationList = em.createQuery("select s from  AdditionalInformation s where s.personId="+personId+" order by s.updatedAt",
                AdditionalInformation.class).getResultList();
        return additionalInformationList.stream();
    }


}
