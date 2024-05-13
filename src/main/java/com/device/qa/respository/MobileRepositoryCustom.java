package com.device.qa.respository;

import com.device.qa.model.AvailabilityStatus;
import com.device.qa.model.Mobile;


public interface MobileRepositoryCustom {
    Mobile getMobileByName(String mobileName);

    Boolean markMobileAsUnavailable(Long id);
    
	boolean updateStatus(Long id, AvailabilityStatus availabilityStatus);
}
