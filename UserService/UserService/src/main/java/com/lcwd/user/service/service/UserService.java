package com.lcwd.user.service.service;

import com.lcwd.user.service.entitys.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    //create
    User saveUser(User user);
    //get all user
    List<User> getAlluser();
    //Get single user
    User getUser(String userId);

}
