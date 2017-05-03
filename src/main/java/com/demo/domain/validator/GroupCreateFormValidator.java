package com.demo.domain.validator;

import com.demo.domain.Group;
import com.demo.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import org.springframework.validation.Validator;

/**
 * @author Ekaterina Pyataeva on 30.04.2017.
 */

@Component
public class GroupCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupCreateFormValidator.class);

    @Autowired
    GroupService groupService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Group.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Group form = (Group) target;
    }

}
