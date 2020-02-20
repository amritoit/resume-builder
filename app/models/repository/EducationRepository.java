package models.repository;

import com.google.inject.ImplementedBy;
import models.Education;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(EducationRepositoryImpl.class)
public interface EducationRepository {
    CompletionStage<Stream<Education>> addEducation(List<Education> education);
    CompletionStage<Stream<Education>> mergeEducation(List<Education> education);
    CompletionStage<Stream<Education>> getEducation(Long personId);
}
