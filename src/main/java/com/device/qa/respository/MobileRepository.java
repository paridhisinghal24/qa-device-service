package com.device.qa.respository;

import org.springframework.data.repository.CrudRepository;

import com.device.qa.model.Mobile;
	public interface MobileRepository extends CrudRepository<Mobile, Long>,MobileRepositoryCustom {

	}
