package com.airnz.email.service;

import com.airnz.email.model.EmailMessage;
import com.airnz.email.model.EmailMessageRequest;
import com.airnz.email.repository.EmailRepository;
import com.airnz.email.util.EmailHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailHelper emailHelper;
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

    public void sendEmail(Long id){
        EmailMessage emailMessage = emailRepository.getEmailMessage(id);

        // mandatory and validation checks
        if (emailMessage.getFrom() == null
                || emailMessage.getFrom().getEmailAddress() == null
                || emailMessage.getFrom().getEmailAddress().trim().length() == 0
                || !emailHelper.isEmailValid(emailMessage.getFrom().getEmailAddress())){
            throw new IllegalStateException("Valid from email address required");
        }

        // mandatory To email address required
        if (emailMessage.getToRecipients() == null
                || emailMessage.getToRecipients().size() == 0){
            throw new IllegalStateException("To email address required");
        }

        // validation check for all email addresses
        List<String> emailAddress = new ArrayList<>();
        emailMessage.getToRecipients().forEach(em -> emailAddress.add(em.getEmailAddress()));
        if (emailMessage.getCcRecipients() !=null && emailMessage.getCcRecipients().size() > 0){
            emailMessage.getCcRecipients().forEach(em -> emailAddress.add(em.getEmailAddress()));
        }
        if (emailMessage.getBccRecipients() !=null && emailMessage.getBccRecipients().size() > 0){
            emailMessage.getBccRecipients().forEach(em -> emailAddress.add(em.getEmailAddress()));
        }
        for(String emailaddr:emailAddress){
            if (!emailHelper.isEmailValid(emailaddr)){
                throw new IllegalStateException("Email address is not valid: "+emailaddr);
            }
        }

        // check email is not sent and a draft
        if (emailMessage.getRead() != null
                || !emailMessage.getDraft()
                || emailMessage.getSentDateTime() != null
                || emailMessage.getReceivedDateTime() != null){
            throw new IllegalStateException("Email is not a draft and has already been sent "+ id);
        }

        // build mailMessage used for sending email
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailMessage.getFrom().getEmailAddress());
        simpleMailMessage.setSubject(emailMessage.getSubject());
        simpleMailMessage.setText(emailMessage.getBody());
        simpleMailMessage.setSentDate(new Date());
        simpleMailMessage.setTo(emailHelper.listToArray(emailMessage.getToRecipients()));
        simpleMailMessage.setCc(emailHelper.listToArray(emailMessage.getCcRecipients()));
        simpleMailMessage.setBcc(emailHelper.listToArray(emailMessage.getBccRecipients()));

        // sending email
        // javaMailSender.send(simpleMailMessage);
        log.info("Email sent successfully.");
    }

    @Override
    public EmailMessage updateEmail(Long id, Map<String, Object> fields) {
        EmailMessage emailMessage = emailRepository.getEmailMessage(id);
        fields.forEach((key, value)->{
            // system some fields should not be allowed to be updated
            if (!key.equals("isRead")
                    && !key.equals("isDraft")
                    && !key.equals("createdDateTime")
                    && !key.equals("receivedDateTime")
                    && !key.equals("sentDateTime")){
                Field field = ReflectionUtils.findField(EmailMessage.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, emailMessage, value);
            }
        });
        return emailMessage;
    }

}
