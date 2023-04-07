package com.example.demo;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private String text;
    private String imageuri;

    private User8 sender;

    private long time;

    public Message(){};

    public Message(String text, User8 sender) {
        this.text = text;
        this.sender = sender;
        Date date = new Date();
        this.time = date.getTime();
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", imageuri='" + imageuri + '\'' +
                '}';
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public User8 getSender() {
        return sender;
    }

    public void setSender(User8 sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }
}