package models.repository;

import com.google.inject.ImplementedBy;
import models.AdditionalInformation;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(AdditionalInformationRepositoryImpl.class)
public interface AdditionalInformationRepository {
    CompletionStage<Stream<AdditionalInformation>> mergeAdditionalInformation(List<AdditionalInformation> additionalInformation);
    CompletionStage<Stream<AdditionalInformation>> addAdditionalInformation(List<AdditionalInformation> additionalInformation);
    CompletionStage<Stream<AdditionalInformation>> getAdditionalInformations(Long personId);
}
