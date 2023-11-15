package com.bav.mynotes.data.notes.repository

import com.bav.mynotes.data.notes.storage.NoteStorage
import com.bav.mynotes.data.notes.util.NoteConverter
import com.bav.mynotes.domain.notes.models.Note
import com.bav.mynotes.domain.notes.repository.NoteRepository

class NoteRepositoryImpl(private val storage: NoteStorage) : NoteRepository {
    override suspend fun getNotes(): List<Note> {
        return storage.get().map { NoteConverter().entityToModel(it) }
    }

    override suspend fun getNoteById(id: String): Note {
        return NoteConverter().entityToModel(storage.getById(id))
    }

    override suspend fun saveNote(note: Note): Boolean {
        return storage.save(NoteConverter().modelToEntity(note))
    }

    override suspend fun updateNote(note: Note): Boolean {
        return storage.update(NoteConverter().modelToEntity(note))
    }

    override suspend fun deleteDote(note: Note): Boolean {
        return storage.delete(NoteConverter().modelToEntity(note))
    }
}
