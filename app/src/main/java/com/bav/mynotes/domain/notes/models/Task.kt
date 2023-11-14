package com.bav.mynotes.domain.notes.models

data class Task(
    val id: String,
    val done: TaskDone = TaskDone.NotDone,
    val content: String,
)

enum class TaskDone {
    Done,
    NotDone,
}
