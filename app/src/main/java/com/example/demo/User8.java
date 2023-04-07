package com.example.demo;


import androidx.annotation.Nullable;

public class User8 {
    private String firstname;
    private String lastname;
    private String email;
    private String imageuri;

    public User8(){

    }

    public User8(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", imageuri='" + imageuri + '\'' +
                '}';
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        User8 other = (User8) obj;
        return getEmail().equals(other.getEmail());
    }

    @Override
    public int hashCode() {
        return getEmail().hashCode();
    }
}