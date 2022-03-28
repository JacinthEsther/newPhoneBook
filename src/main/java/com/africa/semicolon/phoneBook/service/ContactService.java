package com.africa.semicolon.phoneBook.service;

import com.africa.semicolon.phoneBook.data.repository.ContactRepository;
import com.africa.semicolon.phoneBook.dtos.request.ContactRequest;
import com.africa.semicolon.phoneBook.dtos.response.ContactResponse;

import java.util.List;

public interface ContactService {
    ContactResponse saveContact(ContactRequest request);
//    ContactRepository getRepository();
    List<ContactResponse> findContact(String something);
    String deleteContact(String request);
    ContactResponse updateContact(ContactRequest request);

}
