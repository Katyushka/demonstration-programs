package com.demo.web;

import com.demo.domain.Category;
import com.demo.domain.CurrentCategory;
import com.demo.domain.validator.CategoryCreateFormValidator;
import com.demo.service.CategoryService;
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
 * @author Ekaterina Pyataeva on 02.05.2017.
 */

@Controller
public class CategoryController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    protected static final String PATH_ROOT = "/categories";
    protected static final String PATH_CREATE = "/categories/create";
    protected static final String PATH_SAVE = "/categories/save";
    protected static final String PATH_EDIT = "/categories/edit/{categoryId}";
    protected static final String PATH_DELETE = "/categories/delete/{categoryId}";


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryCreateFormValidator categoryCreateFormValidator;

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(categoryCreateFormValidator);
    }


    @RequestMapping(value = PATH_CREATE, method = RequestMethod.GET)
    public String getCategoryCreatePage(Model model) {
        LOGGER.debug("Getting category create form");
        model.addAttribute("form", new Category());
        return "categoryCreate";
    }

    @RequestMapping(value = PATH_CREATE, method = RequestMethod.POST)
    public String handleCategoryCreateForm(@Valid @ModelAttribute("form") Category form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "categoryCreate";
        }
        try {
            Category category = categoryService.create(form);
            CurrentCategory currentCategory = new CurrentCategory(category);

        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the category, assuming duplicate email", e);
            bindingResult.reject("email.exists", "Email already exists");
            return "categoryCreate";
        }
        return "redirect:/categories";
    }

    @RequestMapping(method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public String getCategories(Model model) {
        LOGGER.debug("Getting categories list");
        model.addAttribute("category", new Category());
        return "categories";
    }

    @RequestMapping(value = PATH_SAVE, method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String saveCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult) {
        LOGGER.debug("Getting save category action");
        if (bindingResult.hasErrors()) {
            return "categoryEdit";
        }
        categoryService.save(category);
        return "redirect:/categories";
    }

    @RequestMapping(value = PATH_EDIT, method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public String getCategory(@PathVariable("categoryId") Long categoryId, Model model) {
        LOGGER.debug("Getting get category action" + categoryId);
        Category category = categoryService.getCategoryById(categoryId);
        model.addAttribute("category", category);
        return "categoryEdit";
    }

    @RequestMapping(value = PATH_DELETE, method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String deleteCategory(@PathVariable("categoryId") Long categoryId) {
        LOGGER.debug("Delete category by id action");
        Category category = categoryService.getCategoryById(categoryId);
        categoryService.delete(category);
        return "redirect:/categories";
    }

}
