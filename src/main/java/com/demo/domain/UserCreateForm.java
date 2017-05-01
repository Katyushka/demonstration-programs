package com.demo.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Ekaterina Pyataeva on 24.04.2017.
 */
public class UserCreateForm {
    @NotEmpty
    private String email="";

    @NotEmpty
    private String firstName="";

    @NotEmpty
    private String lastName="";

    @NotEmpty
    private Long gruopId;

    @NotEmpty
    private String password="";

    @NotEmpty
    private String passwordRepeated="";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getGruopId() {
        return gruopId;
    }

    public void setGruopId(Long gruopId) {
        this.gruopId = gruopId;
    }
}
