package com.device.qa.controller;

import com.device.qa.model.User;
import com.device.qa.services.UserService;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	public void greetingShouldReturnMessageFromService() throws Exception {

		when(userService.findById(Mockito.anyLong())).thenReturn(new User(1L,"test", "test@gmail.com", "123"));
		MvcResult result = this.mockMvc
				.perform(get("/api/user/1"))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
		String content = result.getResponse().getContentAsString();
		Assert.assertEquals("{\"id\":1,\"name\":\"test\",\"email\":\"test@gmail.com\",\"phoneNumber\":\"123\"}", content);
	}
}
