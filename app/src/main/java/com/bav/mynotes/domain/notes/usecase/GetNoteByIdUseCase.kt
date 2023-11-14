package com.bav.mynotes.domain.notes.usecase

import com.bav.mynotes.domain.notes.models.Note
import com.bav.mynotes.domain.notes.repository.NoteRepository

class GetNoteByIdUseCase(private val repository: NoteRepository) {

    fun execute(id: String): Note {
        return repository.getNoteById(id)
    }
}
