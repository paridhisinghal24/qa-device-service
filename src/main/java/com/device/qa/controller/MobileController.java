package com.device.qa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.device.qa.exceptions.MobileException;
import com.device.qa.exceptions.UserException;
import com.device.qa.model.AvailabilityStatus;
import com.device.qa.model.Mobile;
import com.device.qa.model.PhoneDetails;
import com.device.qa.services.BookingService;
import com.device.qa.services.MobileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/api/mobile")
public class MobileController {

    @Autowired
    private MobileService mobileService;

    @Autowired
    private BookingService bookingService;
    
    /**
     * Registers a new mobile
     * 
     * @param  Mobile object
     * @return The new created mobile object
     */
    @PostMapping("/{mobile-name}")
    public ResponseEntity<Mobile> register(@PathVariable(name = "mobile-name", 
    	    required = true) String mobileName) {
        return ResponseEntity.ok(mobileService.save(new Mobile(mobileName, AvailabilityStatus.YES)));
    }
    
    
    /**
     * Get all mobile phones
     * @return List of mobile in database
     */
    @GetMapping
    public ResponseEntity<List<Mobile>> getAllMobiles() {
        return ResponseEntity.ok(mobileService.getAll());
    }
    

    /**
     * To book a mobile phone for user
     * 
     * @param mobile-name The mobile name to be booked
     * @return The response saying if mobile booking is success or failure
     * @throws UserException(not found) or MobileException ( not found/not available).
     */
    @PostMapping("/{mobile-name}/book")
    public ResponseEntity<String> bookPhone(@RequestHeader(value = "user-id", 
    required = false) Long userId, @PathVariable(name = "mobile-name", 
    required = true) String mobileName) throws MobileException, UserException {
        var resp  = bookingService.bookPhone(userId,mobileName);
        if(resp) {
    		return new ResponseEntity<String>("Mobile phone is booked successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Mobile Booking failed.", HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    /**
     * To return a mobile phone allocated to a user
     * 
     * @param mobile-name The mobile name to be booked, userId taken from request header
     * @return The response saying if mobile booking is success or failure
     * @throws UserException(not found) or MobileException ( not found/not available).
     */
    @PostMapping("/{mobile-name}/return")
    public ResponseEntity<String> returnPhone(@RequestHeader(value = "user-id", 
    	    required = false) Long userId, @PathVariable(name = "mobile-name", 
    	    required = true) String mobileName) throws MobileException, UserException {
        var resp  = bookingService.returnPhone(userId,mobileName);
        if(resp) {
    		return new ResponseEntity<String>("Mobile phone is successfully returned.", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Mobile phone return failed.", HttpStatus.EXPECTATION_FAILED);
        }
    }
    /**
     *  To get mobile phone details
     * 
     * @param mobile-name The mobile name for which we need details
     * @return The response wtih PhoneDetails , Fonoapi extra details, Booking log
     */
    
    @GetMapping(path = "/{mobile-name}")
    public ResponseEntity<PhoneDetails> getPhoneDetails(@PathVariable(name = "mobile-name", required = true) String mobileName) throws MobileException, JsonMappingException, JsonProcessingException {
        var resp  = bookingService.getPhoneDetails(mobileName);
        return  ResponseEntity.ok(resp);
    }


}
