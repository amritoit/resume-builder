package models.repository;

import com.google.inject.ImplementedBy;
import models.Education;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(EducationRepositoryImpl.class)
public interface EducationRepository {
    CompletionStage<Education> addEducation(Education education);
    CompletionStage<Stream<Education>> getEducation(Long personId);
}
