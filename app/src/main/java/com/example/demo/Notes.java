package com.example.demo;

import java.util.ArrayList;

public class Notes {
    private ArrayList<Note> noteArrayList;

    public Notes() {
    }

    public Notes(ArrayList<Note> noteArrayList) {
        this.noteArrayList = noteArrayList;
    }

    public ArrayList<Note> getNoteArrayList() {
        return noteArrayList;
    }

    public void setNoteArrayList(ArrayList<Note> noteArrayList) {
        this.noteArrayList = noteArrayList;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "noteArrayList=" + noteArrayList +
                '}';
    }
}

