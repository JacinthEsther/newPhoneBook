package com.africa.semicolon.phoneBook.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Document("Contacts")
public class Contact {
    @Id
    private String id;
    @NonNull
    private String firstName;
    private String lastName;
    @NonNull
    private String mobileNumber;
    private String officeNumber;
    private String emailAddress;

    public Contact( String firstName, String lastName,
                    String mobileNumber, String officeNumber,
                   String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.officeNumber = officeNumber;
        this.emailAddress = emailAddress;
    }
}
