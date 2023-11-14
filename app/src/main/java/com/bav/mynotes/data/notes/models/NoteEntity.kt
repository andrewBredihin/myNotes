package com.bav.mynotes.data.notes.models

data class NoteEntity(
    val id: String,
    val title: String,
    val tasks: List<TaskEntity>,
)
