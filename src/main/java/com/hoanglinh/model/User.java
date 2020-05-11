package com.hoanglinh.model;

public class User {
    private String password;
    private String email;

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

    public  User(){
    }
    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
