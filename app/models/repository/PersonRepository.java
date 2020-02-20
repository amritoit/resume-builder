package models.repository;

import com.google.inject.ImplementedBy;
import models.Person;

import java.util.concurrent.CompletionStage;

@ImplementedBy(PersonRepositoryImpl.class)
public interface PersonRepository {
    CompletionStage<Person> add(Person person);
    CompletionStage<Person> merge(Person person);
    CompletionStage<Person> getPerson(Long personId);
}
