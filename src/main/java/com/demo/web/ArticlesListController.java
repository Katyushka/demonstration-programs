package com.demo.web;

import com.demo.domain.Article;
import com.demo.service.ArticleService;
import com.demo.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Ekaterina Pyataeva on 04.05.2017.
 */

@Controller
public class ArticlesListController extends  AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    protected static final String PATH_ROOT = "/articlesList";
    protected static final String PATH_GET = "articlesList/{categoryId}";

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = PATH_ROOT)
    public String getIndexPage(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        model.addAttribute("categories", categoryService.getAllCategories());
        LOGGER.debug("Getting home page");
        return PATH_ROOT;
    }


    @RequestMapping(PATH_GET)
    public String getArticlesByCategory(@PathVariable("categoryId") Long categoryId, Model model) {
        LOGGER.debug("Getting get articles action " + categoryId);
        List<Article> articles = articleService.getArticlesByCategoryId(categoryId);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("articles", articles);
        return "articlesList";
    }

}
