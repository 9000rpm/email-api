package com.airnz.email;

import com.airnz.email.exceptions.InvalidRequestException;
import com.airnz.email.model.EmailAddress;
import com.airnz.email.model.EmailMessage;
import com.airnz.email.model.EmailMessageRequest;
import com.airnz.email.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmailApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EmailService emailService;

	@Test
	@Order(1)
	public void getEmailMessages(){
		List<EmailMessage> emailMessages = emailService.getEmailMessages();
		assertEquals(true, emailMessages.size()>1, "Size of list of emails returned is 2.");
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
		EmailMessageRequest emailMessageRequest = new EmailMessageRequest(
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
		EmailMessage emailMessage = emailService.createEmailDraft(emailMessageRequest);
		assertEquals(true, emailMessage.getId()>1, "Size of list of emails returned must now be 3.");
	}

	@Test
	@Order(4)
	public void sendEmail() throws InvalidRequestException {
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

	@Test
	@Order(6)
	public void getEmailsApi() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/emails")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(7)
	public void getEmailApi() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/emails/1")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(8)
	public void createDraftApi() throws Exception {
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
		ObjectMapper objectMapper = new ObjectMapper();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/emails")
				.content(objectMapper.writeValueAsString(emailMessage))
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isCreated())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(9)
	public void sendEmailApi() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/emails/4/send");
		mockMvc.perform(requestBuilder)
				.andExpect(status().isNoContent())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(10)
	public void updateDraftApi() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/v1/emails/2")
				.content("{\"subject\": \"Penny, please open the door!\"}")
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

}
