package com.airnz.email.service;

import com.airnz.email.model.EmailMessage;
import com.airnz.email.model.EmailMessageRequest;

import java.util.List;

public interface EmailService {
    public List<EmailMessage> getEmailMessages();
    public EmailMessage getEmailMessage(Long id);
    public EmailMessage createEmailDraft(EmailMessageRequest emailMessageRequest);
    public void sendEmail(Long id);
}
