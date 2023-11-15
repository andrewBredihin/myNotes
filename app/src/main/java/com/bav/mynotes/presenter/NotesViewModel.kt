package com.bav.mynotes.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bav.mynotes.domain.notes.usecase.GetNoteByIdUseCase
import com.bav.mynotes.domain.notes.usecase.GetNotesUseCase
import com.bav.mynotes.domain.notes.usecase.SaveNoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
) : ViewModel() {

    private val _notesState = MutableStateFlow<NoteListState>(NoteListState.Default)
    val notesState = _notesState.asStateFlow()

    fun loadNotes() {
        _notesState.value = NoteListState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                delay(3000)
                val list = getNotesUseCase.execute()
                val data = NoteListData(notes = list)
                withContext(Dispatchers.Main) {
                    _notesState.value = NoteListState.DataProvided(data)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _notesState.value = NoteListState.Error(e.stackTrace.contentDeepToString())
                }
            }
        }
    }
}
