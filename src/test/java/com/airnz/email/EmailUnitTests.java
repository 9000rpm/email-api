package com.airnz.email;

import com.airnz.email.model.EmailAddress;
import com.airnz.email.model.EmailMessage;
import com.airnz.email.model.EmailMessageRequest;
import com.airnz.email.service.EmailService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmailUnitTests {

    @Autowired
    private EmailService emailService;

    @Test
    @Order(1)
    public void getEmailMessages(){
        List<EmailMessage> emailMessages = emailService.getEmailMessages();
        assertEquals(2, emailMessages.size(), "Size of list of emails returned is 2.");
    }

    @Test
    @Order(2)
    public void getEmailMessage(){
        EmailMessage emailMessages = emailService.getEmailMessage(1L);
        assertEquals("Bazinga!", emailMessages.getSubject(), "Subject must contain value Bazinga!");
    }

    @Test
    @Order(3)
    public void createEmailDraft(){
        EmailMessageRequest emailMessage = new EmailMessageRequest(
                "That is my spot!",
                "normal",
                false,
                "text/plain",
                "Hi Rajesh, please can you move off my spot!",
                new EmailAddress("sheldon.cooper@mail.com", "Sheldon Cooper"),
                new EmailAddress("sheldon.cooper@mail.com", "Sheldon Cooper"),
                Arrays.asList(new EmailAddress("rajesh@mail.com", "Rajesh"),
                        new EmailAddress("penny@mail.com", "Penny")),
                null,
                null);
        emailService.createEmailDraft(emailMessage);
        assertEquals(3, emailService.getEmailMessages().size(), "Size of list of emails returned must now be 3.");
    }

    @Test
    @Order(4)
    public void sendEmail(){
        emailService.sendEmail(3L);
        EmailMessage emailMessage = emailService.getEmailMessage(3L);
        assertEquals(false, emailMessage.getDraft(), "Email is no longer a draft.");
    }

    @Test
    @Order(5)
    public void updateDraft(){
        Map<String, Object> fields = new HashMap<>();
        fields.put("subject","Penny, please open the door!");
        emailService.updateDraft(2L, fields);
        EmailMessage emailMessage = emailService.getEmailMessage(2L);
        assertEquals("Penny, please open the door!", emailMessage.getSubject(), "Subject must match new subject.");
    }
}
