package com.demo.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Ekaterina Pyataeva on 02.05.2017.
 */
public class CategoryCreateForm {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
