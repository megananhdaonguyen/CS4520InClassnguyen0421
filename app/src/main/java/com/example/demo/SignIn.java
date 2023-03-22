package com.example.demo;

public class SignIn {

    private boolean auth;
    private String token;

    public SignIn() {
    }

    public SignIn(boolean auth, String token) {
        this.auth = auth;
        this.token = token;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
