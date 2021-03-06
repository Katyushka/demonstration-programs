package com.demo.web;

import com.demo.domain.Article;
import com.demo.domain.User;
import com.demo.domain.validator.ArticleCreateFormValidator;
import com.demo.service.ArticleService;
import com.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Ekaterina Pyataeva on 02.05.2017.
 */


@Controller
@RequestMapping(value = "/profile")
public class ProfileController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

    private static String UPLOADED_FOLDER = "E://osu//5_kurs//files//";


    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleCreateFormValidator articleCreateFormValidator;

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(articleCreateFormValidator);
    }


    @RequestMapping(method = RequestMethod.GET)
    public String getArticleCreatePage(Model model) {
        LOGGER.debug("Getting article create form");
        model.addAttribute("form", new Article());
        List<User> users = userService.getAllUsers();
        users.remove(userService.getCurrentUser());
        model.addAttribute("users", users);
        return "profile";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String handleArticleCreateForm(@Valid @ModelAttribute("form") Article form, BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

        byte[] bytes;
        try {
            if (!file.isEmpty()) {
                if (!file.getContentType().equals("application/zip: ZIP")) bindingResult.addError(new FieldError("form","file","Введен неверный формат файла!!!"));
                else {
                    bytes = file.getBytes();
                    Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                    Files.write(path, bytes);
                    form.setContent(bytes);
                    form.setMimeType(file.getContentType());
                    form.setFileName(file.getOriginalFilename());
                }
                form.setSize(file.getSize());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Article article = articleService.create(form);
        articleService.save(article);
        if (bindingResult.hasErrors()) {
            return "profile";
        }
        return "profile";
    }


}
