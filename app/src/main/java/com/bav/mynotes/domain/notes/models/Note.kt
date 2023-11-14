package com.bav.mynotes.domain.notes.models

data class Note(
    val id: String,
    val title: String,
    val tasks: List<Task>,
)
