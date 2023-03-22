package com.example.demo;

import java.io.Serializable;

public class Note implements Serializable {
    private String _id;
    private String userId;
    private String text;
    private int __v;

    public Note() {
    }

    public Note(String _id, String userId, String text, int __v) {
        this._id = _id;
        this.userId = userId;
        this.text = text;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    @Override
    public String toString() {
        return "Note{" +
                "_id='" + _id + '\'' +
                ", userId='" + userId + '\'' +
                ", text='" + text + '\'' +
                ", __v=" + __v +
                '}';
    }
}
