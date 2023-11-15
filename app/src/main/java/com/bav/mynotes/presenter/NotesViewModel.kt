package com.bav.mynotes.presenter

import androidx.lifecycle.ViewModel
import com.bav.mynotes.domain.notes.models.Note
import com.bav.mynotes.domain.notes.usecase.GetNoteByIdUseCase
import com.bav.mynotes.domain.notes.usecase.GetNotesUseCase
import com.bav.mynotes.domain.notes.usecase.SaveNoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotesViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
) : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes = _notes.asStateFlow()

    fun getNotes() {
        _notes.value = getNotesUseCase.execute()
    }
}
