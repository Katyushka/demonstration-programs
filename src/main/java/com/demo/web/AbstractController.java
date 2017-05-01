package com.demo.web;

import com.demo.domain.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author Ekaterina Pyataeva on 24.04.2017.
 */

@Controller
public class AbstractController {

    @Autowired
    protected UserService userService;

    @ModelAttribute("currentUser")
    public User getCurrentUser(){
        return userService.getCurrentUser();
    }

}
