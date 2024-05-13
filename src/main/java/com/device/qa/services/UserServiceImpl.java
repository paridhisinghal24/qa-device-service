package com.device.qa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.device.qa.model.User;
import com.device.qa.respository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findById(Long userId) {
		 Optional<User> dbUser = userRepository.findById(userId);
		 return dbUser.orElse(null);
	}

}
