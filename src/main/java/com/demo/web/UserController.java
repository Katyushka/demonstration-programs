package com.demo.web;

import com.demo.domain.CurrentUser;
import com.demo.domain.User;
import com.demo.domain.validator.UserCreateFormValidator;
import com.demo.service.EmailService;
import com.demo.service.GroupService;
import com.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Ekaterina Pyataeva on 24.04.2017.
 */

@Controller
public class UserController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    protected static final String PATH_ROOT = "/users";
    protected static final String PATH_CREATE = "/user/create";
    protected static final String PATH_SAVE = "/users/save";
    protected static final String PATH_GET = "users/get/{userId}";
    protected static final String PATH_DELETE = "/users/delete/{userId}";

    @Autowired
    private UserCreateFormValidator userCreateFormValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private EmailService emailService;

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @RequestMapping(value = PATH_CREATE, method = RequestMethod.GET)
    public String getUserCreatePage(Model model) {
        LOGGER.debug("Getting user create form");
        model.addAttribute("form", new User());
        model.addAttribute("groups", groupService.getAllGroups());
        return "userCreate";
    }

    @RequestMapping(value = PATH_CREATE, method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") User form, BindingResult bindingResult) {

        LOGGER.debug("Processing user create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            return "userCreate";
        }
        try {
            User user = userService.create(form);
            CurrentUser currentUser = new CurrentUser(user);
            Authentication auth =
                    new UsernamePasswordAuthenticationToken(currentUser, null, currentUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
            emailService.sendMail(currentUser.getUser().getEmail(), "Welcome!", "Thank you for registration :)");
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            bindingResult.reject("email.exists", "Email already exists");
            return "userCreate";
        }
        return "redirect:/";
    }

    @RequestMapping(PATH_ROOT)
    @Secured("ROLE_ADMIN")
    public String getUsers(Model model) {
        LOGGER.debug("Getting users list");
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("groups", groupService.getAllGroups());
        return "users";
    }

    @RequestMapping(value = PATH_SAVE, method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        LOGGER.debug("Getting save user action");
        if (bindingResult.hasErrors()) {
            return "userForm";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @RequestMapping(PATH_GET)
    @Secured("ROLE_ADMIN")
    public String getUser(@PathVariable("userId") Long userId, Model model) {
        LOGGER.debug("Getting get user action" + userId);
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("groups", groupService.getAllGroups());
        model.addAttribute("password", "invisible");
        return "userForm";
    }

    @RequestMapping(value = PATH_DELETE, method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String deleteUser(@PathVariable("userId") Long userId) {
        LOGGER.debug("Delete user by id action");
        User user = userService.getUserById(userId);
        userService.delete(user);
        return "redirect:/users";
    }


}
