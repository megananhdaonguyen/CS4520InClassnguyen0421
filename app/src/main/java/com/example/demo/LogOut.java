package com.example.demo;

public class LogOut {
    private boolean auth;
    private String token;

    public LogOut() {
    }

    public LogOut(boolean auth, String token) {
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
