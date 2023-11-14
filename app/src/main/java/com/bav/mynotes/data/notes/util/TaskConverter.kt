package com.bav.mynotes.data.notes.util

import com.bav.mynotes.data.notes.models.TaskEntity
import com.bav.mynotes.domain.notes.models.Task

class TaskConverter : Converter<TaskEntity, Task> {
    override fun entityToModel(entity: TaskEntity): Task {
        return Task(
            id = entity.id,
            done = entity.done,
            content = entity.content,
        )
    }

    override fun modelToEntity(model: Task): TaskEntity {
        return TaskEntity(
            id = model.id,
            done = model.done,
            content = model.content,
        )
    }
}
