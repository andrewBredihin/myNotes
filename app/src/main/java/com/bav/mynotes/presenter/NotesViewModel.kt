package com.bav.mynotes.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bav.mynotes.domain.notes.usecase.DeleteNoteUseCase
import com.bav.mynotes.domain.notes.usecase.GetNoteByIdUseCase
import com.bav.mynotes.domain.notes.usecase.GetNotesUseCase
import com.bav.mynotes.domain.notes.usecase.SaveNoteUseCase
import com.bav.mynotes.domain.notes.usecase.UpdateNoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
) : ViewModel() {

    private val _notesState = MutableStateFlow<NoteListState>(NoteListState.Default)
    val notesState = _notesState.asStateFlow()

    fun loadNotes() {
        _notesState.value = NoteListState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = NoteListData(notes = getNotesUseCase.execute())
                withContext(Dispatchers.Main) {
                    _notesState.value = NoteListState.NotesProvided(data)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _notesState.value = NoteListState.Error(e.stackTrace.contentDeepToString())
                }
            }
        }
    }

    fun getNoteById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = NoteData(note = getNoteByIdUseCase.execute(id))
                withContext(Dispatchers.Main) {
                    _notesState.value = NoteListState.NoteProvided(data)
                }
            } catch (e: Exception) {
                loadNotes()
            }
        }
    }
}
