package com.mstedler.loginapp.pojo;

public class LoginData {

    private String email;
    private String senha;

    public LoginData() {
    }

    public LoginData(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
