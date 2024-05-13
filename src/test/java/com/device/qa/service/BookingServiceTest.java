package com.device.qa.service;

import com.device.qa.exceptions.MobileException;
import com.device.qa.exceptions.UserException;
import com.device.qa.model.AvailabilityStatus;
import com.device.qa.model.Booking;
import com.device.qa.model.Mobile;
import com.device.qa.model.PhoneBookingRequest;
import com.device.qa.model.PhoneDetails;
import com.device.qa.model.PhoneReturnRequest;
import com.device.qa.model.User;
import com.device.qa.respository.BookingRepository;
import com.device.qa.services.BookingService;
import com.device.qa.services.BookingServiceImpl;
import com.device.qa.services.FonoapiService;
import com.device.qa.services.MobileService;
import com.device.qa.services.UserService;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {
	@Mock
	private BookingRepository bookingRepository;
	
	@Mock
	private MobileService mobileService;
	
	@Mock
	private UserService userService;
	
	@Mock
	private FonoapiService fonoapiService;
	
    @InjectMocks
    private BookingServiceImpl bookingService;

	@Test
	public void testbookPhone_whenUserNotFound_throwsException() throws Exception {

		when(userService.findById(1L)).thenReturn(null);
		assertThrows(UserException.class, () -> {
			bookingService.bookPhone(new PhoneBookingRequest("test",1L));
        });
	}
	
	@Test
	public void testbookPhone_whenMobileNotFound_throwsException() throws Exception {

		when(userService.findById(1L)).thenReturn(new User(1L,"test","test@eg.com","123"));
		when(mobileService.getMobileByName("test")).thenReturn(null);

		assertThrows(MobileException.class, () -> {
			bookingService.bookPhone(new PhoneBookingRequest("test",1L));
        });
	}
	
	@Test
	public void testbookPhone_whenMobileIsBooked_throwsException() throws Exception {

		when(userService.findById(1L)).thenReturn(new User(1L,"test","test@eg.com","123"));
		when(mobileService.getMobileByName("test")).thenReturn(new Mobile(1L,"test", AvailabilityStatus.No));
		assertThrows(MobileException.class, () -> {
			bookingService.bookPhone(new PhoneBookingRequest("test",1L));
        });
	}
	
	@Test
	public void testbookPhone_whenFailToMarkMobileUnavail_throwsException() throws Exception {

		when(userService.findById(1L)).thenReturn(new User(1L,"test","test@eg.com","123"));
		when(mobileService.getMobileByName("test")).thenReturn(new Mobile(1L,"test", AvailabilityStatus.YES));
		when(mobileService.markMobileAsUnavailable(1L)).thenReturn(false);
		assertThrows(MobileException.class, () -> {
			bookingService.bookPhone(new PhoneBookingRequest("test",1L));
        });
	}
	
	@Test
	public void testbookPhone_whenAllSuccess_throwsException() throws Exception {

		when(userService.findById(1L)).thenReturn(new User(1L,"test","test@eg.com","123"));
		when(mobileService.getMobileByName("test")).thenReturn(new Mobile(1L,"test", AvailabilityStatus.YES));
		when(mobileService.markMobileAsUnavailable(1L)).thenReturn(true);
		when(bookingRepository.save(Mockito.any(Booking.class))).thenReturn(new Booking());
		assertEquals(true, bookingService.bookPhone(new PhoneBookingRequest("test",1L)));
	}
	
	@Test
	public void testReturnPhone_whenUserNotFound_throwsException() throws Exception {

		when(userService.findById(1L)).thenReturn(null);
		assertThrows(UserException.class, () -> {
			bookingService.returnPhone(new PhoneReturnRequest("test",1L));
        });
	}
	
	@Test
	public void testReturnPhone_whenMobileNotFound_throwsException() throws Exception {

		when(userService.findById(1L)).thenReturn(new User(1L,"test","test@eg.com","123"));
		when(mobileService.getMobileByName("test")).thenReturn(null);

		assertThrows(MobileException.class, () -> {
			bookingService.returnPhone(new PhoneReturnRequest("test",1L));
        });
	}
	

	
	@Test
	public void testReturnPhone_whenFailToMarkMobileAvail_throwsException() throws Exception {

		when(userService.findById(1L)).thenReturn(new User(1L,"test","test@eg.com","123"));
		when(mobileService.getMobileByName("test")).thenReturn(new Mobile(1L,"test", AvailabilityStatus.YES));
		when(mobileService.updateStatus(1L, AvailabilityStatus.YES)).thenReturn(false);
		assertThrows(MobileException.class, () -> {
			bookingService.returnPhone(new PhoneReturnRequest("test",1L));
        });
	}
	
	@Test
	public void testReturnPhone_whenAllSuccess_ReturnTrue() throws Exception {

		when(userService.findById(1L)).thenReturn(new User(1L,"test","test@eg.com","123"));
		when(mobileService.getMobileByName("test")).thenReturn(new Mobile(1L,"test", AvailabilityStatus.YES));
		when(mobileService.updateStatus(1L, AvailabilityStatus.YES)).thenReturn(true);
		assertEquals(true, bookingService.returnPhone(new PhoneReturnRequest("test",1L)));
	}
	
	@Test
	public void testGetPhoneDetails_whenAllSuccess_ReturnPhoneDetails() throws Exception {
		when(bookingRepository.getBookingLog(1L)).thenReturn(null);
		when(mobileService.getMobileByName("test")).thenReturn(new Mobile(1L,"test", AvailabilityStatus.YES));
		when(fonoapiService.getMobileData("test")).thenReturn(null);
		PhoneDetails phoneDetails = bookingService.getPhoneDetails("test");
		assertEquals(null, phoneDetails.getBookingLog());
		assertEquals(null, phoneDetails.getOtherDetails());
		assertEquals(new Mobile(1L,"test", AvailabilityStatus.YES), phoneDetails.getMobile());
	}
	@Test
	public void testGetPhoneDetails_whenMobileNotFound_ThrowsException() throws Exception {
		when(mobileService.getMobileByName("test")).thenReturn(null);
        assertThrows(MobileException.class, () -> {
        	bookingService.getPhoneDetails("test");
        });
	}
}
