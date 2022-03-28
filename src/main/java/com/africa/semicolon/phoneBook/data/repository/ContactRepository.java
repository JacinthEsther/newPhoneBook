package com.africa.semicolon.phoneBook.data.repository;

import com.africa.semicolon.phoneBook.data.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends MongoRepository<Contact,String> {
 boolean existsByMobileNumber(String  mobileNumber);

}
