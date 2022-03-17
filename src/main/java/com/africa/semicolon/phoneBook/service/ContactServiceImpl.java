package com.africa.semicolon.phoneBook.service;

import com.africa.semicolon.phoneBook.data.model.Contact;
import com.africa.semicolon.phoneBook.data.repository.ContactRepository;
import com.africa.semicolon.phoneBook.dtos.request.ContactRequest;
import com.africa.semicolon.phoneBook.dtos.response.ContactResponse;
import com.africa.semicolon.phoneBook.exceptions.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
   private ContactRepository contacts;


    @Override
    public ContactResponse saveByFirstNameAndMobileNumber(ContactRequest request) {

        if(request.getFirstName().equals(" ") || request.getMobileNumber().equals(" ")){
//            System.out.println(request.getFirstName());
            throw new InvalidRequestException("Field cannot be empty");
        }
            Contact contact = new Contact(request.getFirstName(), request.getMobileNumber());

        Contact contactToBeSaved = contacts.save(contact);

        ContactResponse response = new ContactResponse();
        response.setFirstName(contactToBeSaved.getFirstName());
        response.setMobileNumber(contactToBeSaved.getMobileNumber());
        return response;


    }
//
//    private ContactResponse getContactResponse(Contact contact) {
//        Contact contactToBeSaved = contacts.save(contact);
//
//        ContactResponse response = new ContactResponse();
//        response.setFirstName(contactToBeSaved.getFirstName());
//        response.setMobileNumber(contactToBeSaved.getMobileNumber());
//        return response;
//    }

    @Override
    public ContactResponse saveContact(ContactRequest request) {
        Contact contact1 = new Contact(request.getFirstName(), request.getLastName(),
        request.getMobileNumber(), request.getOfficeNumber(), request.getEmailAddress());
        Contact contactToBeSaved = contacts.save(contact1);

        ContactResponse response = new ContactResponse();
        response.setFirstName(contactToBeSaved.getFirstName());
        response.setLastName(contactToBeSaved.getLastName());
        response.setMobileNumber(contactToBeSaved.getMobileNumber());
        response.setOfficeNumber(contactToBeSaved.getOfficeNumber());
        response.setEmailAddress(contactToBeSaved.getEmailAddress());

        return response;
    }

    @Override
    public ContactRepository getRepository() {
        return contacts;

    }

    @Override
    public List<ContactResponse> findContact(String something) {
        List<ContactResponse> matchingContacts = new ArrayList<>();
        System.out.println(Arrays.toString(contacts.findAll().toArray()));
        for (Contact contact: contacts.findAll()){
            System.out.println("firstname----> " + contact.getFirstName());
            System.out.println("lastname----> " + contact.getLastName());

            if (contact.getFirstName().equalsIgnoreCase(something) ||
            contact.getLastName().equalsIgnoreCase(something)||
                     contact.getMobileNumber().equalsIgnoreCase(something)){

                ContactResponse response = new ContactResponse();
                response.setFirstName(contact.getFirstName());
                response.setLastName(contact.getLastName());
                response.setMobileNumber(contact.getMobileNumber());
                response.setEmailAddress(contact.getEmailAddress());
                matchingContacts.add(response);
            }
            else throw new InvalidRequestException(something + " does not exist");
        }
        return matchingContacts;


    }



}
