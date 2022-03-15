package com.vector.notetakingapp.repository;

import com.vector.notetakingapp.model.Note;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note,Integer> {
    
}
