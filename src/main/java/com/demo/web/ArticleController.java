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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Ekaterina Pyataeva on 02.05.2017.
 */


@Controller
@RequestMapping(value = "/article")
public class ArticleController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;


    @RequestMapping(value = "/{articleId}")
    public String getArticleById(@PathVariable("articleId") Long articleId, Model model) {
        LOGGER.debug("Getting get articles action " + articleId);
        Article article = articleService.getArticleById(articleId);
        article.setJumpCount(article.getJumpCount()+1);
        articleService.save(article);
        model.addAttribute("article", article);
        return "article";
    }


    @RequestMapping(value = "/download/{articleId}")
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
}
