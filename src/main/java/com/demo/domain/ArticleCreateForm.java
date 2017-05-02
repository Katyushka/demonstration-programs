package com.demo.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author Ekaterina Pyataeva on 02.05.2017.
 */
public class ArticleCreateForm {

    @NotEmpty
    private String name = "";

    @NotEmpty
    private String description = "";

    @NotNull
    private byte[] content;

    @NotNull
    private Category category;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
