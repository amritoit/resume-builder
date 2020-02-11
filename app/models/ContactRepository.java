package models;

import com.google.inject.ImplementedBy;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(ContactRepositoryImpl.class)
public interface ContactRepository {
    CompletionStage<Contact> addContact(Contact contact);
    CompletionStage<Contact> getContact(Long personId);
    CompletionStage<Stream<SocialLink>> getSocialLink(Long personId, Long contactId);
}