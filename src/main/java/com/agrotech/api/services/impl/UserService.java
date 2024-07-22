package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.UserRepository;
import com.agrotech.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<String> getFarmerByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(User::getFarmer);
    }
}
