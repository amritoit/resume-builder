package models.repository;

import com.google.inject.ImplementedBy;
import models.Workinfo;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(WorkRepositoryImpl.class)
public interface WorkRepository {
    CompletionStage<Stream<Workinfo>> addWork(List<Workinfo> workInfo);
    CompletionStage<Stream<Workinfo>> getWorks(Long personId);
}
