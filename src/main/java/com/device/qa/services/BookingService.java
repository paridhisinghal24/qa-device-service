package com.device.qa.services;

import com.device.qa.exceptions.MobileException;
import com.device.qa.exceptions.UserException;
import com.device.qa.model.PhoneBookingRequest;
import com.device.qa.model.PhoneDetails;
import com.device.qa.model.PhoneReturnRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
public interface BookingService {

  public Boolean bookPhone(PhoneBookingRequest phoneBookingRequest) throws MobileException, UserException;

  public Boolean returnPhone(PhoneReturnRequest phoneBookingRequest) throws MobileException, UserException;
  
  public PhoneDetails getPhoneDetails(String mobileName) throws MobileException, JsonMappingException, JsonProcessingException;

}
