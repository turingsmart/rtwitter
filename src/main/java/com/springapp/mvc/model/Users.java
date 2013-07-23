package com.springapp.mvc.model;

import com.sun.istack.internal.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Users: maverick
 * Date: 15/7/13
 * Time: 12:35 PM
 * To change this template use File | Settings | File Templates.
 */

public class Users {

    @NotNull
    private String username;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @NotNull
    private String dateofjoining;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateofjoining() {
        return dateofjoining;
    }

    public void setDateofjoining(String dateofjoining) {
        this.dateofjoining = dateofjoining;
    }
}
