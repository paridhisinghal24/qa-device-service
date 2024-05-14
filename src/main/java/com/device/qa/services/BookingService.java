package com.device.qa.services;

import com.device.qa.exceptions.MobileException;
import com.device.qa.exceptions.UserException;
import com.device.qa.model.PhoneDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
public interface BookingService {

  public Boolean bookPhone(Long userId, String mobileName ) throws MobileException, UserException;

  public Boolean returnPhone(Long userId, String mobileName ) throws MobileException, UserException;
  
  public PhoneDetails getPhoneDetails(String mobileName) throws MobileException, JsonMappingException, JsonProcessingException;

}
