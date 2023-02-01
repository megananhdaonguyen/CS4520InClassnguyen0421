package com.example.demo;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    String email;
    int avatar;
    String use;
    String mood;
    int moodImage;

    public User(String name, String email, int avatar, String use, String mood, int moodImage) {
        this.name = name;
        this.email = email;
        this.avatar = avatar;
        this.use = use;
        this.mood = mood;
        this.moodImage = moodImage;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name= " + name +
                ", email= " + email +
                ", use= " + use +
                ", mood= " + mood +
                "}";
    }
}
