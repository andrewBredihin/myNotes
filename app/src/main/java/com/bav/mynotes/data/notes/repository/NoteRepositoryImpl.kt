package com.bav.mynotes.data.notes.repository

import com.bav.mynotes.data.notes.storage.NoteStorage
import com.bav.mynotes.data.notes.util.NoteConverter
import com.bav.mynotes.domain.notes.models.Note
import com.bav.mynotes.domain.notes.repository.NoteRepository

class NoteRepositoryImpl(private val storage: NoteStorage) : NoteRepository {
    override fun getNotes(): List<Note> {
        return storage.get().map { NoteConverter().entityToModel(it) }
    }

    override fun getNoteById(id: String): Note {
        return NoteConverter().entityToModel(storage.getById(id))
    }

    override fun saveNote(note: Note): Boolean {
        return storage.save(NoteConverter().modelToEntity(note))
    }

    override fun updateNote(note: Note): Boolean {
        return storage.update(NoteConverter().modelToEntity(note))
    }
}
