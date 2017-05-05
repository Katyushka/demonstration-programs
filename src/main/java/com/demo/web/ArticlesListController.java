package com.demo.web;

import com.demo.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ekaterina Pyataeva on 04.05.2017.
 */

@Controller
public class ArticlesListController extends  AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    protected static final String PATH_ROOT = "/articlesList";

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = PATH_ROOT)
    public String getIndexPage(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        LOGGER.debug("Getting home page");
        return PATH_ROOT;
    }


}
