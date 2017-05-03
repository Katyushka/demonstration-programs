package com.demo.domain.validator;

import com.demo.domain.User;
import com.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Ekaterina Pyataeva on 24.04.2017.
 */

@Component
public class UserCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreateFormValidator.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        User form = (User) target;
        validatePassword(errors, form);
        validateLogin(errors, form);
    }

    private void validateLogin(Errors errors, User form) {
        if (userService.getUserByEmail(form.getEmail()) != null) {
            errors.reject("User with this email already exist");
        }
    }

    private void validatePassword(Errors errors, User form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password.no_match", "Passwords do not match");
        }
    }

}