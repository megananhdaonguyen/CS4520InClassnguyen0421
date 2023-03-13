package com.example.demo;

public class News {
    private String title;
    private String author;
    private String publishedAt;
    private String description;
    private String urlToImage;

    public News(){

    }

    public News(String title, String author, String publishedAt, String description, String urlToImage) {
        this.title = title;
        this.author = author;
        this.publishedAt = publishedAt;
        this.description = description;
        this.urlToImage = urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getpublishedAt() {
        return publishedAt;
    }

    public void setpublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return urlToImage;
    }

    public void setImageURL(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    @Override
    public String toString() {
        return "TITLE: " + title + "\n\n" +
                "AUTHOR: " + author + "\n\n" +
                "DATE & TIME OF PUBLICATION: " + publishedAt + "\n\n" +
                "DESCRIPTION: " + description + "\n\n" +
                "IMAGE: " + urlToImage + "\n\n\n\n\n\n\n\n";
    }
}
