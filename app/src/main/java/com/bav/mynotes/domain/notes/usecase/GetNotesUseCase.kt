package com.bav.mynotes.domain.notes.usecase

import com.bav.mynotes.domain.notes.models.Note
import com.bav.mynotes.domain.notes.repository.NoteRepository

class GetNotesUseCase(private val repository: NoteRepository) {

    fun execute(): List<Note> {
        return repository.getNotes()
    }
}
