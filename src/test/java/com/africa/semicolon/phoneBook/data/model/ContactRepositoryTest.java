package com.africa.semicolon.phoneBook.data.model;

import com.africa.semicolon.phoneBook.data.repository.ContactRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;


@DataMongoTest
public class ContactRepositoryTest {
    @Autowired
    private ContactRepository repository;
    
    @Test
    public void saveContactTest(){
        Contact contact = new Contact();
        contact.setFirstName("Esther");
        contact.setLastName("Esther");
        contact.setEmailAddress("Esther");
        contact.setMobileNumber("Esther");
        contact.setOfficeNumber("Esther");
        Contact savedContact = repository.save(contact);
        assertNotNull(savedContact.getId());

        assertThat(savedContact.getId(), is(notNullValue()));
        assertThat(repository.count(), is(1L));
    }
}
