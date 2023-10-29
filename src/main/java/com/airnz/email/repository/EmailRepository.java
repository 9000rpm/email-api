package com.airnz.email.repository;

import com.airnz.email.model.EmailAddress;
import com.airnz.email.model.EmailMessage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmailRepository implements CommandLineRunner {

    private final List<EmailMessage> emailMessages = new ArrayList<>();

    public List<EmailMessage> getEmailMessages(){
        return emailMessages;
    }

    public EmailMessage getEmailMessage(Long id)
    {
        List<EmailMessage> result = emailMessages.stream()
                                            .filter(value -> value.getId() == id)
                                            .collect(Collectors.toList());
        if (result.size() != 1) {
            throw new IllegalStateException("Could not find exactly one user, got: " + result);
        }
        return result.get(0);
    }

    public EmailMessage createEmailDraft(EmailMessage emailMessage){
        emailMessages.add(emailMessage);
        return emailMessage;
    }
    @Override
    public void run(String...args) throws Exception {
        EmailMessage emailMessage = new EmailMessage(
                1L,
                "Bazinga!",
                new Date(),
                new Date(),
                new Date(),
                "high",
                true,
                false,
                false,
                "text/plain",
                "Hi Leonard, Please call me urgently. Thanks Sheldon",
                new EmailAddress("sheldon.cooper@mail.com", "Sheldon Cooper"),
                new EmailAddress("sheldon.cooper@mail.com", "Sheldon Cooper"),
                Arrays.asList(new EmailAddress("leonard.hofstadter@mail.com", "Leonard Hofstadter"),
                              new EmailAddress("penny@mail.com", "Penny")),
                null,
                null);
        emailMessages.add(emailMessage);
        emailMessage = new EmailMessage(
                2L,
                "Knock, knock, knock!",
                new Date(),
                new Date(),
                new Date(),
                "high",
                false,
                false,
                false,
                "text/plain",
                "Penny, Penny, Penny!",
                new EmailAddress("sheldon.cooper@mail.com", "Sheldon Cooper"),
                new EmailAddress("sheldon.cooper@mail.com", "Sheldon Cooper"),
                Arrays.asList(new EmailAddress("penny@mail.com", "penny")),
                null,
                null);
        emailMessages.add(emailMessage);
    }
}
