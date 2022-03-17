package com.africa.semicolon.phoneBook.controller;

import com.africa.semicolon.phoneBook.dtos.request.ContactRequest;
import com.africa.semicolon.phoneBook.dtos.response.ApiResponse;
import com.africa.semicolon.phoneBook.dtos.response.ContactResponse;
import com.africa.semicolon.phoneBook.exceptions.InvalidRequestException;
import com.africa.semicolon.phoneBook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
   private ContactService contactService;

    @PostMapping("/saveContact")
    public ResponseEntity<?> response(@RequestBody ContactRequest request){
        try {
            return new ResponseEntity<>(contactService.saveByFirstNameAndMobileNumber(request)
            , HttpStatus.CREATED);
        }
        catch(InvalidRequestException ex){
            return new ResponseEntity<>(new ApiResponse(false,ex.getMessage()),
                    HttpStatus.NO_CONTENT);
        }
    }
     @PostMapping("/saveContact/save")
    public ResponseEntity<?> ContactResponse(@RequestBody ContactRequest request){
//       return contactService.saveContact(request);
         try {
             return new ResponseEntity<>(contactService.saveContact(request)
                     , HttpStatus.CREATED);
         }
         catch(InvalidRequestException ex){
             return new ResponseEntity<>(new ApiResponse(false,ex.getMessage()),
                     HttpStatus.NO_CONTENT);
         }
    }

    @GetMapping("/{name}")
    public List<ContactResponse> findContact(@PathVariable("name") String name){
        return contactService.findContact(name);
    }

}
