package com.demo.service;

import com.demo.domain.Article;
import com.demo.domain.User;
import com.demo.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Ekaterina Pyataeva on 02.05.2017.
 */

@Service
@Transactional
public class ArticleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleService.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserService userService;


    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();
        articles.addAll(articleRepository.findAll());
        return articles;
    }

    public List<Article> getArticlesByCategoryId(Long categoryId) {
        List<Article> articles = new ArrayList<>();
        articles.addAll(articleRepository.findByCategoryId(categoryId));
        return articles;
    }

    public Article getArticleById(Long id) {
        return articleRepository.findOne(id);
    }



    public Article create(Article form) {
        List<User> users = form.getUsers();
        users.add(userService.getCurrentUser());
        form.setUsers(users);
        return articleRepository.save(form);
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }

}
