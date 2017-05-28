package com.demo.web;

import com.demo.domain.Article;
import com.demo.service.ArticleService;
import com.demo.util.ArticleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Ekaterina Pyataeva on 02.05.2017.
 */


@Controller
public class ArticleController extends AbstractController {

    protected static final String PATH_ROOT = "/articles";
    protected static final String PATH_GET = "/article/{articleId}";
    protected static final String PATH_DOWNLOAD = "/article/download/{articleId}";
    protected static final String PATH_EDIT = "/articles/edit/{articleId}";
    protected static final String PATH_SAVE = "/articles/save";
    protected static final String PATH_DELETE = "/articles/delete/{articleId}";

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;


    @RequestMapping(value = PATH_ROOT, method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public String getArticles(Model model) {
        LOGGER.debug("Getting articles list");
        model.addAttribute("article", new Article());
        model.addAttribute("articles", articleService.getAllArticles());
        return "articles";
    }

    @RequestMapping(value = PATH_GET)
    public String getArticleById(@PathVariable("articleId") Long articleId, Model model) {
        LOGGER.debug("Getting get articles action " + articleId);
        Article article = articleService.getArticleById(articleId);
        article.setJumpCount(article.getJumpCount()+1);
        articleService.save(article);
        model.addAttribute("article", article);
        return "article";
    }


    @RequestMapping(value = PATH_DOWNLOAD)
    public ResponseEntity<byte[]> getFile(@PathVariable("articleId") Long articleId, Model model) {
        HttpHeaders headers = new HttpHeaders();
        Article article = articleService.getArticleById(articleId);
        article.setDownloadCount(article.getDownloadCount()+1);
        if (article == null) {
            return ArticleUtils.get404RedirectEntity(headers);
        }
        byte[] file = article.getContent();
        if (file == null) {
            return ArticleUtils.get404RedirectEntity(headers);
        }
        ArticleUtils.setHeaders(headers, article.getMimeType(), article.getFileName());
        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }

    @RequestMapping(PATH_EDIT)
    @Secured("ROLE_ADMIN")
    public String getArticle(@PathVariable("articleId") Long articleId, Model model) {
        LOGGER.debug("Getting get article action" + articleId);
        Article article = articleService.getArticleById(articleId);
        model.addAttribute("form", article);
        model.addAttribute("password", "invisible");
        return "articleEdit";
    }


    @RequestMapping(value = PATH_SAVE, method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String saveArticle(@Valid @ModelAttribute("article") Article article, BindingResult bindingResult) {
        LOGGER.debug("Getting save article action");
        if (bindingResult.hasErrors()) {
            return "articleEdit";
        }
        Article articleById = articleService.getArticleById(article.getId());
        articleById.setName(article.getName());
        articleById.setDescription(article.getDescription());
        articleById.setRegistrationNumber(article.getRegistrationNumber());
        articleService.save(articleById);
        return "redirect:/articles";
    }

    @RequestMapping(value = PATH_DELETE,  method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String deleteArticle (@PathVariable("articleId") Long articleId) {
        LOGGER.debug("Delete user by id action");
        Article article = articleService.getArticleById(articleId);
        articleService.delete(article);
        return "redirect:/articles";
    }

}
