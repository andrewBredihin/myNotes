package com.bav.mynotes.data.notes.storage.test

import com.bav.mynotes.data.notes.models.NoteEntity
import com.bav.mynotes.data.notes.models.TaskEntity
import com.bav.mynotes.data.notes.storage.NoteStorage

class TestStorage : NoteStorage {

    private var _note = NoteEntity(
        id = "1",
        title = "Test",
        tasks = listOf(
            TaskEntity(id = "1", content = "Test1"),
            TaskEntity(id = "2", content = "Test"),
            TaskEntity(id = "3", content = "Test"),
        ),
    )

    override suspend fun get(): List<NoteEntity> {
        val tasks = listOf(
            TaskEntity(id = "1", content = "Test1"),
            TaskEntity(id = "2", content = "Test"),
            TaskEntity(id = "3", content = "Test"),
        )
        return listOf(
            NoteEntity(id = "1", title = "Test", tasks = tasks),
            NoteEntity(id = "2", title = "Test", tasks = tasks),
            NoteEntity(id = "3", title = "Test", tasks = tasks),
            NoteEntity(id = "4", title = "Test", tasks = tasks),
            NoteEntity(id = "5", title = "Test", tasks = tasks),
            NoteEntity(id = "6", title = "Test", tasks = tasks),
            NoteEntity(id = "7", title = "Test", tasks = tasks),
            NoteEntity(id = "8", title = "Test", tasks = tasks),
            NoteEntity(id = "9", title = "Test", tasks = tasks),
            NoteEntity(id = "10", title = "Test", tasks = tasks),
            NoteEntity(id = "11", title = "Test", tasks = tasks),
            NoteEntity(id = "12", title = "Test", tasks = tasks),
            NoteEntity(id = "13", title = "Test", tasks = tasks),
            NoteEntity(id = "14", title = "Test", tasks = tasks),
            NoteEntity(id = "15", title = "Test", tasks = tasks),
            NoteEntity(id = "16", title = "Test", tasks = tasks),
            NoteEntity(id = "17", title = "Test", tasks = tasks),
            NoteEntity(id = "18", title = "Test", tasks = tasks),
            NoteEntity(id = "19", title = "Test", tasks = tasks),
            NoteEntity(id = "20", title = "Test", tasks = tasks),
            NoteEntity(id = "21", title = "Test", tasks = tasks),
            NoteEntity(id = "22", title = "Test", tasks = tasks),
            NoteEntity(id = "23", title = "Test", tasks = tasks),
            NoteEntity(id = "24", title = "Test", tasks = tasks),
            NoteEntity(id = "25", title = "Test", tasks = tasks),
            NoteEntity(id = "26", title = "Test", tasks = tasks),
            NoteEntity(id = "27", title = "Test", tasks = tasks),
        )
    }

    override suspend fun getById(id: String): NoteEntity {
        return _note
    }

    override suspend fun save(note: NoteEntity): Boolean {
        return true
    }

    override suspend fun update(note: NoteEntity): Boolean {
        _note = note
        return true
    }

    override suspend fun delete(note: NoteEntity): Boolean {
        return true
    }
}
