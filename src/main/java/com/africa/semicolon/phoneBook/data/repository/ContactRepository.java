package com.africa.semicolon.phoneBook.data.repository;

import com.africa.semicolon.phoneBook.data.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContactRepository extends MongoRepository<Contact,String> {

//    List<Contact> findContactBy(String something);
}
