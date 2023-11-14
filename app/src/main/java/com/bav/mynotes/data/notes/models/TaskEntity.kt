package com.bav.mynotes.data.notes.models

import com.bav.mynotes.domain.notes.models.TaskDone

data class TaskEntity(
    val id: String,
    val done: TaskDone = TaskDone.NotDone,
    val content: String,
)
