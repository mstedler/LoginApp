package com.mstedler.loginapp.pojo;

public class User {
    private String name;
    private String token;
    private String photo_url;

    public User(String name, String token, String photo_url) {
        this.name = name;
        this.token = token;
        this.photo_url = photo_url;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
