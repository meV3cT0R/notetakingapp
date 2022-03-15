package com.vector.notetakingapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Note {
    private @Id @GeneratedValue int id;
    private String noteData;
  
    public int getId() {
        return id;
    }
    public String getNoteData() {
        return noteData;
    }
    public void setNoteData(String noteData) {
        this.noteData = noteData;
    }
    public void setId(int id) {
        this.id = id;
    }


    
}
