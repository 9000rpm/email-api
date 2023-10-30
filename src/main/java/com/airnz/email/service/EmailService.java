package com.airnz.email.service;

import com.airnz.email.exceptions.InvalidRequestException;
import com.airnz.email.model.EmailMessage;
import com.airnz.email.model.EmailMessageRequest;

import java.util.List;
import java.util.Map;

public interface EmailService {
    List<EmailMessage> getEmailMessages();
    EmailMessage getEmailMessage(Long id);
    EmailMessage createEmailDraft(EmailMessageRequest emailMessageRequest);
    void sendEmail(Long id) throws InvalidRequestException;
    EmailMessage updateDraft(Long id, Map<String, Object> fields);
}
