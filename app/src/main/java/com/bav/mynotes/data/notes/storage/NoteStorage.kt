package com.bav.mynotes.data.notes.storage

import com.bav.mynotes.data.notes.models.NoteEntity

interface NoteStorage {

    suspend fun get(): List<NoteEntity>
    suspend fun getById(id: String): NoteEntity
    suspend fun save(note: NoteEntity): Boolean
    suspend fun update(note: NoteEntity): Boolean
}
