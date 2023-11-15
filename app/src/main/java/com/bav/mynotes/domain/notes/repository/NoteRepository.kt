package com.bav.mynotes.domain.notes.repository

import com.bav.mynotes.domain.notes.models.Note

interface NoteRepository {

    suspend fun getNotes(): List<Note>

    suspend fun getNoteById(id: String): Note

    suspend fun saveNote(note: Note): Boolean

    suspend fun updateNote(note: Note): Boolean
}
