package com.africa.semicolon.phoneBook.service;

import com.africa.semicolon.phoneBook.data.model.Contact;
import com.africa.semicolon.phoneBook.data.repository.ContactRepository;
import com.africa.semicolon.phoneBook.dtos.request.ContactRequest;
import com.africa.semicolon.phoneBook.dtos.response.ContactResponse;
import com.africa.semicolon.phoneBook.exceptions.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
   private ContactRepository contacts;

    @Override
    public ContactResponse saveContact(ContactRequest request) throws InvalidRequestException{

        Contact contact1 = new Contact(request.getFirstName(), request.getLastName(),
        request.getMobileNumber(), request.getOfficeNumber(), request.getEmailAddress());
        if (contacts.existsByMobileNumber(request.getMobileNumber())) {
            throw new InvalidRequestException("Contact already exist");
        }
        else {
            Contact contactToBeSaved = contacts.save(contact1);

            ContactResponse response = new ContactResponse();
            response.setFirstName(contactToBeSaved.getFirstName());
            response.setLastName(contactToBeSaved.getLastName());
            response.setMobileNumber(contactToBeSaved.getMobileNumber());
            response.setOfficeNumber(contactToBeSaved.getOfficeNumber());
            response.setEmailAddress(contactToBeSaved.getEmailAddress());

            return response;
        }
    }


    @Override
    public List<ContactResponse> findContact(String something) {
        List<ContactResponse> matchingContacts = new ArrayList<>();
        for (Contact contact: contacts.findAll()){

            if (contact.getFirstName().equalsIgnoreCase(something) ||
                    contact.getLastName().equalsIgnoreCase(something)||
                    contact.getMobileNumber().equalsIgnoreCase(something)) {

                ContactResponse response = new ContactResponse();
                response.setFirstName(contact.getFirstName());
                response.setLastName(contact.getLastName());
                response.setMobileNumber(contact.getMobileNumber());
                response.setEmailAddress(contact.getEmailAddress());
                matchingContacts.add(response);
            }
        }
        if(matchingContacts.size()<1) throw new InvalidRequestException(" not found");
        return matchingContacts;
    }

    @Override
    public String deleteContact(String request) {
        String response = "";

        for (Contact contact : contacts.findAll()) {
            if (contact.getMobileNumber().equals(request)){
                contacts.delete(contact);
                response = "deleted successfully!!!";
            }
        }
        return response;
    }

    @Override
    public ContactResponse updateContact(ContactRequest request) {
        Contact contact = new Contact(request.getFirstName(),request.getLastName()
        , request.getMobileNumber(), request.getOfficeNumber(), request.getEmailAddress());

        for (Contact checkContact: contacts.findAll()) {
            if (contacts.existsByMobileNumber(request.getMobileNumber())) {
                  contacts.delete(checkContact);
                  contacts.save(contact);
            }
        }
        ContactResponse response = new ContactResponse();
        response.setFirstName(contact.getFirstName());
        response.setLastName(contact.getLastName());
        response.setMobileNumber(contact.getMobileNumber());
        response.setEmailAddress(contact.getEmailAddress());

        return response;
    }


}
