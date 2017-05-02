package com.demo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Ekaterina Pyataeva on 24.04.2017.
 */

@Entity
@Table(name = "category")
public class Category extends BasicEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Article> articles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}
