package com.bav.mynotes.domain.notes.usecase

import com.bav.mynotes.domain.notes.models.Note
import com.bav.mynotes.domain.notes.repository.NoteRepository

class SaveNoteUseCase(private val repository: NoteRepository) {

    fun execute(note: Note): Boolean {
        return repository.saveNote(note)
    }
}
