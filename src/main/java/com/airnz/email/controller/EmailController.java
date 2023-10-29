package com.airnz.email.controller;

import com.airnz.email.model.EmailMessage;
import com.airnz.email.model.EmailMessageRequest;
import com.airnz.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @GetMapping("/v1/emails")
    public ResponseEntity<List<EmailMessage>> getEmails(){
        List<EmailMessage> emailMessages = emailService.getEmailMessages();
        return new ResponseEntity<>(emailMessages, HttpStatus.OK);
    }

    @GetMapping("/v1/emails/{id}")
    public ResponseEntity<EmailMessage> getEmail(@PathVariable(required = true) Long id){
        EmailMessage emailMessage = emailService.getEmailMessage(id);
        return new ResponseEntity<>(emailMessage, HttpStatus.OK);
    }

    @PostMapping("/v1/emails")
    public ResponseEntity<EmailMessage> createEmailDraft(@RequestBody EmailMessageRequest emailMessageRequest){
        EmailMessage emailMessage = emailService.createEmailDraft(emailMessageRequest);
        return new ResponseEntity<>(emailMessage, HttpStatus.CREATED);
    }
}
