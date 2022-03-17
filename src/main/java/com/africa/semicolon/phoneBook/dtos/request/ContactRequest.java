package com.africa.semicolon.phoneBook.dtos.request;

import lombok.Data;

@Data
public class ContactRequest {

    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String officeNumber;
    private String emailAddress;
}
