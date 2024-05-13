package com.device.qa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.device.qa.model.AvailabilityStatus;
import com.device.qa.model.Mobile;
import com.device.qa.respository.MobileRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MobileServiceImpl implements MobileService{

	@Autowired
	private MobileRepository mobileRepository;

	@Override
	public List<Mobile> getAll() {
	    List<Mobile> mobiles = new ArrayList<>();
	    mobileRepository.findAll().forEach(mobiles::add);
	    return mobiles;
  	}

	@Override
	public Mobile save(Mobile p) {
	    return mobileRepository.save(p);
	}
	@Override
	public Mobile getMobileByName(String mobileName) {
	    return mobileRepository.getMobileByName(mobileName);
	}
	@Override
	public Boolean markMobileAsUnavailable(Long id) {
		return mobileRepository.markMobileAsUnavailable(id);
	}

	@Override
	public boolean updateStatus(Long id, AvailabilityStatus availabilityStatus) {
		return mobileRepository.updateStatus(id, availabilityStatus);
	}

	

}
