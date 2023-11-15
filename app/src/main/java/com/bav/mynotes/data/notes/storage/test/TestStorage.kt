package com.bav.mynotes.data.notes.storage.test

import com.bav.mynotes.data.notes.models.NoteEntity
import com.bav.mynotes.data.notes.models.TaskEntity
import com.bav.mynotes.data.notes.storage.NoteStorage

class TestStorage : NoteStorage {
    override suspend fun get(): List<NoteEntity> {
        val tasks = listOf(TaskEntity(id = "1", content = "Test"))
        return listOf(NoteEntity(id = "1", title = "Test", tasks = tasks))
    }

    override suspend fun getById(id: String): NoteEntity {
        val tasks = listOf(TaskEntity(id = "1", content = "Test"))
        return NoteEntity(id = "1", title = "Test", tasks = tasks)
    }

    override suspend fun save(note: NoteEntity): Boolean {
        return true
    }

    override suspend fun update(note: NoteEntity): Boolean {
        return true
    }
}
