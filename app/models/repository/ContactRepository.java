package models.repository;

import com.google.inject.ImplementedBy;
import models.Contact;

import java.util.concurrent.CompletionStage;

@ImplementedBy(ContactRepositoryImpl.class)
public interface ContactRepository {
    CompletionStage<Contact> mergeContact(Contact contact);
    CompletionStage<Contact> addContact(Contact contact);
    CompletionStage<Contact> getContact(Long personId);
}