package com.bav.mynotes.data.notes.util

interface Converter<Entity, Model> {
    fun entityToModel(entity: Entity): Model

    fun modelToEntity(model: Model): Entity
}
