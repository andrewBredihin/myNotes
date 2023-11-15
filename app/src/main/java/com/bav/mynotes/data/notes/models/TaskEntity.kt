package com.bav.mynotes.data.notes.models

data class TaskEntity(
    val id: String,
    val done: Boolean = false,
    val content: String,
)
