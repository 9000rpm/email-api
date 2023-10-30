package com.airnz.email;

import com.airnz.email.model.EmailAddress;
import com.airnz.email.model.EmailMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
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
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class EmailApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getEmails() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/emails")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getEmail() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/emails/1")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.subject").value("Bazinga!"))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void createDraft() throws Exception {
		EmailMessage emailMessage = new EmailMessage(
				null,
				"That is my spot!",
				null,
				null,
				null,
				"normal",
				null,
				null,
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
	public void sendEmail() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/emails/3/send");
		mockMvc.perform(requestBuilder)
				.andExpect(status().isNoContent())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void updateDraft() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/v1/emails/2")
				.content("{\"subject\": \"Penny, please open the door!\"}")
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.subject").value("Penny, please open the door!"))
				.andDo(MockMvcResultHandlers.print());
	}

}
