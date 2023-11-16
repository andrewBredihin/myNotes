package com.bav.mynotes.presenter.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bav.mynotes.domain.notes.usecase.DeleteNoteUseCase
import com.bav.mynotes.domain.notes.usecase.GetNotesUseCase
import com.bav.mynotes.domain.notes.usecase.UpdateNoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
) : ViewModel() {

    private val _notesState = MutableStateFlow<NoteListState>(NoteListState.Default)
    val notesState = _notesState.asStateFlow()

    fun loadNotes() {
        _notesState.value = NoteListState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            delay(100)
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
}
