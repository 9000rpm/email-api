package com.airnz.email.controller;

import com.airnz.email.exceptions.InvalidRequestException;
import com.airnz.email.model.EmailMessage;
import com.airnz.email.model.EmailMessageRequest;
import com.airnz.email.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class EmailController {

    Logger log = LoggerFactory.getLogger(EmailController.class);
    @Autowired
    private EmailService emailService;

    @GetMapping("/v1/emails")
    public ResponseEntity<List<EmailMessage>> getEmails(){
        List<EmailMessage> emailMessages = emailService.getEmailMessages();
        return new ResponseEntity<>(emailMessages, HttpStatus.OK);
    }

    @GetMapping("/v1/emails/{id}")
    public ResponseEntity<EmailMessage> getEmail(@PathVariable Long id){
        EmailMessage emailMessage = emailService.getEmailMessage(id);
        return new ResponseEntity<>(emailMessage, HttpStatus.OK);
    }

    @PostMapping("/v1/emails")
    public ResponseEntity<EmailMessage> createEmailDraft(@RequestBody EmailMessageRequest emailMessageRequest){
        EmailMessage emailMessage = emailService.createEmailDraft(emailMessageRequest);
        return new ResponseEntity<>(emailMessage, HttpStatus.CREATED);
    }

    // This API will send an already existing draft email
    @PostMapping("/v1/emails/{id}/send")
    public ResponseEntity<Void> sendEmail(@PathVariable Long id){
        try{
            emailService.sendEmail(id);
        } catch (InvalidRequestException error){
            log.error("Error: ", error);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/v1/emails/{id}")
    public ResponseEntity<EmailMessage> updateDraft(@PathVariable Long id,@RequestBody Map<String, Object> fields){
        EmailMessage emailMessage = emailService.updateDraft(id, fields);
        return new ResponseEntity<>(emailMessage, HttpStatus.OK);
    }
}
