package com.example.demo;

public class DeleteNote {
    private Boolean delete;
    private String message;

    public DeleteNote() {
    }

    public DeleteNote(Boolean delete, String message) {
        this.delete = delete;
        this.message = message;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
