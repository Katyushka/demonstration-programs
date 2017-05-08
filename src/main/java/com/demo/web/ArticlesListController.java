package com.demo.web;

import com.demo.domain.Article;
import com.demo.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/articlesList")
public class ArticlesListController extends  AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticlesListController.class);

    protected static final String PATH_GET = "articlesList/{categoryId}";

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public String getIndexPage(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        model.addAttribute("categories", categoryService.getAllCategories());
        LOGGER.debug("Getting home page");
        return "articlesList";
    }


    @RequestMapping(value = "/{categoryId}")
    public String getArticlesByCategory(@PathVariable("categoryId") Long categoryId, Model model) {
        LOGGER.debug("Getting get articles action " + categoryId);
        List<Article> articles = articleService.getArticlesByCategoryId(categoryId);
        model.addAttribute("articles", articles);
        return "articlesList";
    }

}
