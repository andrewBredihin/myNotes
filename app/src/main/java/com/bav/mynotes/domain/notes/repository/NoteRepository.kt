package com.bav.mynotes.domain.notes.repository

import com.bav.mynotes.domain.notes.models.Note

interface NoteRepository {

    fun getNotes(): List<Note>

    fun getNoteById(id: String): Note

    fun saveNote(note: Note): Boolean

    fun updateNote(note: Note): Boolean
}
