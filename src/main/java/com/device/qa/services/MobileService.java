package com.device.qa.services;

import java.util.List;

import com.device.qa.model.AvailabilityStatus;
import com.device.qa.model.Mobile;
public interface MobileService {
  public List<Mobile> getAll();
  
  public Mobile save(Mobile mobile);
  
  public Mobile getMobileByName(String mobileName);
  
  public Boolean markMobileAsUnavailable(Long id);

  public boolean updateStatus(Long id, AvailabilityStatus availabilityStatus);


}
