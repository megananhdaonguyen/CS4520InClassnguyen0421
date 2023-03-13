package com.example.demo;

import java.util.ArrayList;

public class NewsList {
    private ArrayList<News> articles;

    public NewsList() {
    }

    public ArrayList<News> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<News> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "" + articles +"";
    }

}
