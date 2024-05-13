package com.device.qa.respository;

import com.device.qa.model.Booking;
import java.util.List;

public interface BookingRepositoryCustom {
    List<Booking> getBookingLog(Long mobileId);
}
