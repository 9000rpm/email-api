package com.airnz.email.service;

import com.airnz.email.model.EmailMessage;
import com.airnz.email.model.EmailMessageRequest;
import com.airnz.email.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public EmailMessage createEmailDraft(EmailMessageRequest emailMessageRequest){
        // map and create new email message from the request
        EmailMessage emailMessage = new EmailMessage(
                (long) (getEmailMessages().size() + 1), // getting the next sequence
                emailMessageRequest.getSubject(),
                new Date(), // created date time
                null,
                null,
                emailMessageRequest.getImportance(),
                null,
                true, // new message is always created as a draft
                emailMessageRequest.getHasAttachments(),
                emailMessageRequest.getContentType(),
                emailMessageRequest.getBody(),
                emailMessageRequest.getSender(),
                emailMessageRequest.getFrom(),
                emailMessageRequest.getToRecipients(),
                emailMessageRequest.getCcRecipients(),
                emailMessageRequest.getBccRecipients()
        );
        return  emailRepository.createEmailDraft(emailMessage);
    }

}
