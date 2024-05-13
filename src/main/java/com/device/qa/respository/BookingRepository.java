package com.device.qa.respository;

import org.springframework.data.repository.CrudRepository;

import com.device.qa.model.Booking;

public interface BookingRepository extends CrudRepository<Booking, Long>, BookingRepositoryCustom {

}
