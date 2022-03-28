package com.africa.semicolon.phoneBook.controller;

import com.africa.semicolon.phoneBook.dtos.request.ContactRequest;
import com.africa.semicolon.phoneBook.dtos.response.ApiResponse;
import com.africa.semicolon.phoneBook.exceptions.InvalidRequestException;
import com.africa.semicolon.phoneBook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
   private ContactService contactService;

     @PostMapping("/saveContact/save")
    public ResponseEntity<?> ContactResponse(@RequestBody ContactRequest request){

         try {
             return new ResponseEntity<>(contactService.saveContact(request)
                     , HttpStatus.CREATED);
         }
         catch(InvalidRequestException ex){
             return new ResponseEntity<>(new ApiResponse(false,ex.getMessage()),
                     HttpStatus.ALREADY_REPORTED);
         }
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> findContact(@PathVariable("name") String name){

        try {
            return new ResponseEntity<>(contactService.findContact(name)
                    , HttpStatus.FOUND);
        }
        catch(InvalidRequestException ex){
            return new ResponseEntity<>(new ApiResponse(false,ex.getMessage()),
                    HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{phoneNumber}")
    public ResponseEntity<?> delete(@PathVariable("phoneNumber") String phoneNumber){

        return new ResponseEntity<>(contactService.deleteContact(phoneNumber),HttpStatus.OK);
    }

    @PatchMapping("/{updateContact}")
    public ResponseEntity <?> editContact(@RequestBody ContactRequest request){
        try {
            return new ResponseEntity<>(contactService.updateContact(request)
                    , HttpStatus.CREATED);
        }
        catch(InvalidRequestException ex){
            return new ResponseEntity<>(new ApiResponse(false,ex.getMessage()),
                    HttpStatus.ALREADY_REPORTED);
        }
    }

}
