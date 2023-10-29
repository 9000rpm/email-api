package com.airnz.email.service;

import com.airnz.email.model.EmailMessage;
import com.airnz.email.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Override
    public List<EmailMessage> getEmailMessages(){
        return emailRepository.getEmailMessages();
    }

    @Override
    public EmailMessage getEmailMessage(Long id){
        return emailRepository.getEmailMessage(id);
    }
    @Override
    public EmailMessage createEmailDraft(EmailMessage emailMessage){
        return  emailRepository.createEmailDraft(emailMessage);
    }

}
