package com.demo.domain.validator;

import com.demo.domain.CategoryCreateForm;
import com.demo.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import org.springframework.validation.Validator;

/**
 * @author Ekaterina Pyataeva on 02.05.2017.
 */

@Component
public class CategoryCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryCreateFormValidator.class);

    @Autowired
    CategoryService categoryService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CategoryCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryCreateForm form = (CategoryCreateForm) target;
    }


}
