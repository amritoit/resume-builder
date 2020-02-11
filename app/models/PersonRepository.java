package models;

import com.google.inject.ImplementedBy;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(PersonRepositoryImpl.class)
public interface PersonRepository {
    CompletionStage<Person> add(Person person);
    CompletionStage<Person> getPerson(Long personId);
}
