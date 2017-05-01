package com.demo.service;

import com.demo.domain.Role;
import com.demo.domain.User;
import com.demo.domain.UserCreateForm;
import com.demo.repository.UserRepository;
import com.demo.util.SecurityHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ekaterina Pyataeva on 24.04.2017.
 */

@Service
@Transactional
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User getUserById(long id) {
        LOGGER.debug("Getting user ={}", id);
        return userRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public User getCurrentUser(){
        User user = SecurityHelper.getCurrentUser();
        if (user == null){
            return null;
        }
        return getUserById(user.getId());
    }

    public User getUserByEmail(String email) {
        LOGGER.debug("Getting user by name ={}", email);
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        LOGGER.debug("Getting all users");
        List<User> users = new ArrayList<>();
        for (User user:userRepository.findAll()){
            users.add(user);
        }
        return users;
    }

    public User create(UserCreateForm form) {
        LOGGER.debug("Creating new user");
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(Role.ROLE_USER);
        return userRepository.save(user);
    }

    public User save(User user) {
        LOGGER.debug("Saving user");
        if (user.getId() == null){
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    public void delete(User user) {
        LOGGER.debug("Deleting user");
        userRepository.delete(user);
    }

}
