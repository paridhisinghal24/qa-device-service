package com.device.qa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.device.qa.exceptions.MobileException;
import com.device.qa.exceptions.UserException;
import com.device.qa.model.PhoneBookingRequest;
import com.device.qa.model.PhoneDetails;
import com.device.qa.model.PhoneReturnRequest;
import com.device.qa.services.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<String> bookPhone(@RequestBody PhoneBookingRequest phoneBookingRequest) throws MobileException, UserException {
        var resp  = bookingService.bookPhone(phoneBookingRequest);
        if(resp) {
    		return new ResponseEntity<String>("Mobile phone is booked successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Mobile Booking failed .", HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    @PostMapping("/return")
    public ResponseEntity<String> returnPhone(@RequestBody PhoneReturnRequest phoneBookingRequest) throws MobileException, UserException {
        var resp  = bookingService.returnPhone(phoneBookingRequest);
        if(resp) {
    		return new ResponseEntity<String>("Mobile phone is successfully returned.", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Mobile phone return failed .", HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    @GetMapping(path = "/mobile/{mobile-name}")
    public ResponseEntity<PhoneDetails> getPhoneDetails(@PathVariable(name = "mobile-name", required = true) String mobileName) throws MobileException, JsonMappingException, JsonProcessingException {
        var resp  = bookingService.getPhoneDetails(mobileName);
        return  ResponseEntity.ok(resp);
//        if(resp) {
//    		return new ResponseEntity<String>("Mobile phone is booked successfully.", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<String>("Mobile Booking failed .", HttpStatus.EXPECTATION_FAILED);
//
//        }
    }
    
}
