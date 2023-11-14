package com.bav.mynotes.data.notes.storage

import com.bav.mynotes.data.notes.models.NoteEntity

interface NoteStorage {

    fun get(): List<NoteEntity>

    fun getById(id: String): NoteEntity

    fun save(note: NoteEntity): Boolean

    fun update(note: NoteEntity): Boolean
}
