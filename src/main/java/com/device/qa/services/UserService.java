package com.device.qa.services;

import com.device.qa.model.User;
public interface UserService {
  
  public User save(User user);
  
  public User findById(Long userId);

}
