package com.demo.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Ekaterina Pyataeva on 30.04.2017.
 */
public class GroupCreateForm {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
