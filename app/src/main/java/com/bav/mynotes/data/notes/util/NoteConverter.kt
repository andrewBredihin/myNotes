package com.bav.mynotes.data.notes.util

import com.bav.mynotes.data.notes.models.NoteEntity
import com.bav.mynotes.domain.notes.models.Note

class NoteConverter : Converter<NoteEntity, Note> {
    override fun entityToModel(entity: NoteEntity): Note {
        return Note(
            id = entity.id,
            title = entity.title,
            tasks = entity.tasks.map { TaskConverter().entityToModel(it) },
        )
    }

    override fun modelToEntity(model: Note): NoteEntity {
        return NoteEntity(
            id = model.id,
            title = model.title,
            tasks = model.tasks.map { TaskConverter().modelToEntity(it) },
        )
    }
}
