package com.demo.web;

import com.demo.domain.Article;
import com.demo.service.ArticleService;
import com.demo.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Ekaterina Pyataeva on 02.05.2017.
 */


@Controller
public class ArticleController extends AbstractController{

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    protected static final String PATH_ROOT = "/article";
    protected static final String PATH_GET = "article/{articleId}";


    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(PATH_GET)
    public String getArticlesByCategory(@PathVariable("articleId") Long articleId, Model model) {
        LOGGER.debug("Getting get articles action " + articleId);
        Article article = articleService.getArticleById(articleId);
        model.addAttribute("article", article);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "article";
    }

}
