package com.demo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Ekaterina Pyataeva on 23.04.2017.
 */

@Entity
@Table(name = "article")
public class Article extends BasicEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private byte[] content;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_article",
            joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<User> users;


    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
