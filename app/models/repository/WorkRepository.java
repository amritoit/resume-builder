package models.repository;

import com.google.inject.ImplementedBy;
import models.Work;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(WorkRepositoryImpl.class)
public interface WorkRepository {
    CompletionStage<Work> addWork(Work work);
    CompletionStage<Stream<Work>> getWorks(Long personId);
}
