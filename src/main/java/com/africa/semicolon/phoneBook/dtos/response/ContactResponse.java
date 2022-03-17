package com.africa.semicolon.phoneBook.dtos.response;

import lombok.Data;

@Data
public class ContactResponse {

    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String officeNumber;
    private String emailAddress;

}
