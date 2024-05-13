package com.device.qa.respository;

import org.springframework.data.repository.CrudRepository;

import com.device.qa.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
