package com.demo.web;

import com.demo.domain.CurrentGroup;
import com.demo.domain.Group;
import com.demo.domain.validator.GroupCreateFormValidator;
import com.demo.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Ekaterina Pyataeva on 30.04.2017.
 */
@Controller
public class GroupController extends AbstractController{

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupController.class);

    protected static final String PATH_ROOT = "/groups";
    protected static final String PATH_CREATE = "/groups/create";
    protected static final String PATH_SAVE = "/groups/save";
    protected static final String PATH_EDIT = "/groups/edit/{groupId}";
    protected static final String PATH_DELETE = "/groups/delete/{groupId}";

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupCreateFormValidator groupCreateFormValidator;

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(groupCreateFormValidator);
    }


    @RequestMapping(value = PATH_CREATE, method = RequestMethod.GET)
    public String getGroupCreatePage(Model model) {
        LOGGER.debug("Getting group create form");
        model.addAttribute("form", new Group());
        return "groupCreate";
    }

    @RequestMapping(value = PATH_CREATE, method = RequestMethod.POST)
    public String handleGroupCreateForm(@Valid @ModelAttribute("group") Group group, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "groupCreate";
        }
        try {
            Group group1 = groupService.create(group);
            CurrentGroup currentGroup = new CurrentGroup(group1);

        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the group, assuming duplicate email", e);
            bindingResult.reject("email.exists", "Email already exists");
            return "groupCreate";
        }
        return "redirect:/groups";
    }

    @RequestMapping(PATH_ROOT)
    @Secured("ROLE_ADMIN")
    public String getGroups(Model model) {
        LOGGER.debug("Getting groups list");
        model.addAttribute("group", new Group());
        model.addAttribute("groups", groupService.getAllGroups());
        return "groups";
    }

    @RequestMapping(value = PATH_SAVE, method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String saveGroup(@Valid @ModelAttribute("group") Group group, BindingResult bindingResult) {
        LOGGER.debug("Getting save group action");
        if (bindingResult.hasErrors()) {
            return "groupEdit";
        }
        groupService.save(group);
        return "redirect:/groups";
    }

    @RequestMapping(value = PATH_EDIT, method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public String getGroup(@PathVariable("groupId") Long groupId, Model model) {
        LOGGER.debug("Getting get group action" + groupId);
        Group group = groupService.getGroupById(groupId);
        model.addAttribute("group", group);

        return "groupEdit";
    }

    @RequestMapping(value = PATH_DELETE, method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String deleteGroup(@PathVariable("groupId") Long groupId) {
        LOGGER.debug("Delete group by id action");
        Group group = groupService.getGroupById(groupId);
        groupService.delete(group);
        return "redirect:/groups";
    }


}
