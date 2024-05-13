package com.device.qa.respository;

import com.device.qa.model.Booking;

import java.util.List;

import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class BookingRepositoryCustomImpl implements BookingRepositoryCustom {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Booking> getBookingLog(Long mobileId) {
		try 
		{
			Query query = entityManager.createNativeQuery("SELECT * FROM booking_log " +
					"WHERE mobileid = ?", Booking.class);
			query.setParameter(1, mobileId);
			return (List<Booking> )query.getResultList();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	
}
