package com.device.qa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.device.qa.*;
import com.device.qa.exceptions.MobileException;
import com.device.qa.exceptions.UserException;
import com.device.qa.model.AvailabilityStatus;
import com.device.qa.model.Booking;
import com.device.qa.model.FonoapiResponse;
import com.device.qa.model.Mobile;
import com.device.qa.model.PhoneBookingRequest;
import com.device.qa.model.PhoneDetails;
import com.device.qa.model.PhoneReturnRequest;
import com.device.qa.model.User;
import com.device.qa.respository.BookingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService{

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private MobileService mobileService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private FonoapiService fonoapiService;
    
	@Transactional
	@Override
	public Boolean bookPhone(PhoneBookingRequest phoneBookingRequest) throws MobileException, UserException {
		User user = userService.findById(phoneBookingRequest.getUserId());
		if( user == null ) {
			throw new UserException("User not found exception");
		}
		System.out.println(user);
		Mobile mobile = mobileService.getMobileByName(phoneBookingRequest.getMobileName());
		System.out.println(mobile);
		if(mobile!=null && mobile.getAvailabilityStatus() == AvailabilityStatus.YES) {
			Booking booking = new Booking(phoneBookingRequest.getUserId(), mobile.getId() , Instant.now());
		    // Mark the mobile phone unavailable and then update the booking log
			if( mobileService.markMobileAsUnavailable(mobile.getId())) {
				bookingRepository.save(booking);
				 return true;
			} else {
				throw new MobileException("Mobile not found or not available exception");
			}
		} else {
			throw new MobileException("Mobile not found or not available exception");
		}
	}

	@Override
	public Boolean returnPhone(PhoneReturnRequest phoneReturnRequest) throws MobileException, UserException {
		User user = userService.findById(phoneReturnRequest.getUserId());
		if( user == null ) {
			throw new UserException("User not found exception");
		}
		System.out.println(user);
		Mobile mobile = mobileService.getMobileByName(phoneReturnRequest.getMobileName());
		System.out.println(mobile);
		if(mobile!=null) {
		    // Mark the mobile phone available
			if( mobileService.updateStatus(mobile.getId(), AvailabilityStatus.YES)) {
				 return true;
			} else {
				throw new MobileException("Mobile not found or not available exception");
			}
		} else {
			throw new MobileException("Mobile not found or not available exception");
		}
	}

	@Override
	public PhoneDetails getPhoneDetails(String mobileName) throws MobileException, JsonMappingException, JsonProcessingException {
		Mobile mobile = mobileService.getMobileByName(mobileName);
		if( mobile == null ) {
			throw new MobileException("Mobile not found exception");
		}
		List<Booking> bookingList = bookingRepository.getBookingLog(mobile.getId());
		FonoapiResponse fonoapiResponse = fonoapiService.getMobileData(mobileName);
		return new PhoneDetails(mobile,fonoapiResponse,bookingList);
	}
}
