package com.demo.domain.validator;


import com.demo.domain.ArticleCreateForm;
import com.demo.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Ekaterina Pyataeva on 02.05.2017.
 */

@Component
public class ArticleCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleCreateFormValidator.class);

    @Autowired
    ArticleService articleService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(ArticleCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ArticleCreateForm form = (ArticleCreateForm) target;
    }

}
