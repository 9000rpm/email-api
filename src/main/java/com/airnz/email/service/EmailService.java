package com.airnz.email.service;

import com.airnz.email.model.EmailMessage;
import com.airnz.email.model.EmailMessageRequest;

import java.util.List;

public interface EmailService {
    List<EmailMessage> getEmailMessages();
    EmailMessage getEmailMessage(Long id);
    EmailMessage createEmailDraft(EmailMessageRequest emailMessageRequest);
}
