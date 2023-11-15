package com.bav.mynotes.domain.notes.models

data class Task(
    val id: String,
    val done: Boolean = false,
    val content: String,
)
