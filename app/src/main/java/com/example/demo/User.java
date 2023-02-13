package com.example.demo;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class User implements Parcelable {
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

    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
        avatar = in.readInt();
        use = in.readString();
        mood = in.readString();
        moodImage = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public void setName(String newName) {
        this.name = newName;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public void setAvatar(int newAvatar) {
        this.avatar = newAvatar;
    }

    public void setUse(String newUse) {
        this.use = newUse;
    }

    public void setMood(String newMood) {
        this.mood = newMood;
    }

    public void setMoodImage(int newMoodImg) {
        this.moodImage = newMoodImg;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUse() {
        return use;
    }

    public String getMood() {
        return mood;
    }

    public int getAvatar() {
        return avatar;
    }

    public int getMoodImage() {
        return moodImage;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(use);
        parcel.writeString(mood);
        parcel.writeInt(avatar);
        parcel.writeInt(moodImage);
    }
}
