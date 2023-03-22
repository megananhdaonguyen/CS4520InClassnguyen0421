package com.example.demo;

public class AddNote {
    private Boolean posted;
    private Note note;

    public AddNote() {
    }

    public AddNote(Boolean posted, Note note) {
        this.posted = posted;
        this.note = note;
    }

    public Boolean getPosted() {
        return posted;
    }

    public void setPosted(Boolean posted) {
        this.posted = posted;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "AddNote{" +
                "posted=" + posted +
                ", note=" + note +
                '}';
    }
}

