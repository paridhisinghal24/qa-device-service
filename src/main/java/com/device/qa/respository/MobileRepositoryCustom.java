package com.device.qa.respository;

import com.device.qa.model.AvailabilityStatus;
import com.device.qa.model.Mobile;


public interface MobileRepositoryCustom {
    public Mobile getMobileByName(String mobileName);

    public Boolean markMobileAsUnavailable(Long id);
    
    public Boolean updateStatus(Long id, AvailabilityStatus availabilityStatus);
}
