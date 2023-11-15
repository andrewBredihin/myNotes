package com.bav.mynotes.presenter

import com.bav.mynotes.domain.notes.models.Note

sealed class NoteListState {

    object Default : NoteListState()
    object Loading : NoteListState()
    class DataProvided(val data: NoteListData) : NoteListState()
    class Error(val message: String) : NoteListState()
}

class NoteListData(
    val notes: List<Note>,
)
