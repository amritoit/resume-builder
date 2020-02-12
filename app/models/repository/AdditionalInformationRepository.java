package models.repository;

import com.google.inject.ImplementedBy;
import models.AdditionalInformation;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(AdditionalInformationRepositoryImpl.class)
public interface AdditionalInformationRepository {
    CompletionStage<AdditionalInformation> addAdditionalInformation(AdditionalInformation additionalInformation);
    CompletionStage<Stream<AdditionalInformation>> getAdditionalInformations(Long personId);
}
