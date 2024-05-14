package com.device.qa.controller;

import com.device.qa.model.AvailabilityStatus;
import com.device.qa.model.Mobile;
import com.device.qa.services.BookingService;
import com.device.qa.services.MobileService;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(MobileController.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class MobileControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
    private MobileService mobileService;

	@MockBean
    private BookingService bookingService;
	
	@Test
	public void testMobileController_getAllMobiles_returnsNoMobile() throws Exception {
		when(mobileService.getAll()).thenReturn(new ArrayList<Mobile>());
		MvcResult result = this.mockMvc
				.perform(get("/api/mobile"))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
		String content = result.getResponse().getContentAsString();
		assertEquals("[]", content);
	}
	
	@Test
	public void testMobileController_getAllMobiles_returnsMobile() throws Exception {
		List<Mobile> list = new ArrayList<>();
		list.add(new Mobile(1L,"test",AvailabilityStatus.No));
		when(mobileService.getAll()).thenReturn(list);
		MvcResult result = this.mockMvc
				.perform(get("/api/mobile"))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
		String content = result.getResponse().getContentAsString();
		assertEquals("[{\"id\":1,\"mobileName\":\"test\",\"availabilityStatus\":\"No\"}]", content);
	}
	
	@Test
	public void testMobileController_bookMobile_returnsSuccess() throws Exception {
		List<Mobile> list = new ArrayList<>();
		list.add(new Mobile(1L,"test",AvailabilityStatus.No));
		when(bookingService.bookPhone(1L, "test")).thenReturn(true);
		mockMvc.perform(post("/api/mobile/test/book")
                .contentType(MediaType.APPLICATION_JSON)
                .header("user-id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Mobile phone is booked successfully."));    	
	}
	
	@Test
	public void testMobileController_bookMobile_returnsFailure() throws Exception {
		List<Mobile> list = new ArrayList<>();
		list.add(new Mobile(1L,"test",AvailabilityStatus.No));
		when(bookingService.bookPhone(1L, "test")).thenReturn(false);
		mockMvc.perform(post("/api/mobile/test/book")
                .contentType(MediaType.APPLICATION_JSON)
                .header("user-id", "1"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("Mobile Booking failed."));    	
	}
	
	@Test
	public void testMobileController_returnPhone_returnsSuccess() throws Exception {
		List<Mobile> list = new ArrayList<>();
		list.add(new Mobile(1L,"test",AvailabilityStatus.No));
		when(bookingService.returnPhone(1L, "test")).thenReturn(true);
		mockMvc.perform(post("/api/mobile/test/return")
                .contentType(MediaType.APPLICATION_JSON)
                .header("user-id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Mobile phone is successfully returned."));    	
	}
	
	@Test
	public void testMobileController_returnPhone_returnsFailure() throws Exception {
		List<Mobile> list = new ArrayList<>();
		list.add(new Mobile(1L,"test",AvailabilityStatus.No));
		when(bookingService.returnPhone(1L, "test")).thenReturn(false);
		mockMvc.perform(post("/api/mobile/test/return")
                .contentType(MediaType.APPLICATION_JSON)
                .header("user-id", "1"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("Mobile phone return failed."));    	
	}
}
