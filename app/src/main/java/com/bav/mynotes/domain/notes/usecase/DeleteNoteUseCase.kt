package com.bav.mynotes.domain.notes.usecase

import com.bav.mynotes.domain.notes.models.Note
import com.bav.mynotes.domain.notes.repository.NoteRepository

class DeleteNoteUseCase(private val repository: NoteRepository) {

    suspend fun execute(note: Note): Boolean {
        return repository.deleteDote(note)
    }
}
