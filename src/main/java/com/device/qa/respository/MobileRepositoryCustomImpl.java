package com.device.qa.respository;

import com.device.qa.model.AvailabilityStatus;
import com.device.qa.model.Mobile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class MobileRepositoryCustomImpl implements MobileRepositoryCustom {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Mobile getMobileByName(String mobileName) {
		try 
		{
			Query query = entityManager.createNativeQuery("SELECT * FROM mobile  " +
					"WHERE mobile_name = ?", Mobile.class);
			query.setParameter(1, mobileName);
			return (Mobile) query.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	@Modifying
	@Override
	public Boolean markMobileAsUnavailable(Long id) {
		Query query = entityManager.createNativeQuery("Update mobile set availability_status=?" +
				" WHERE id = ? and availability_status=?", Mobile.class);
		query.setParameter(1, AvailabilityStatus.No);
		query.setParameter(2, id);
		query.setParameter(3, AvailabilityStatus.YES);
		return ( query.executeUpdate()==1 )?true: false;
	}

	@Modifying
	@Override
	public boolean updateStatus(Long id, AvailabilityStatus availabilityStatus) {
		Query query = entityManager.createNativeQuery("Update mobile set availability_status=?" +
				" WHERE id = ?", Mobile.class);
		query.setParameter(1, availabilityStatus);
		query.setParameter(2, id);
		return ( query.executeUpdate()==1 )?true: false;
	}
}
