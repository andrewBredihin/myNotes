package com.bav.mynotes.presenter

import com.bav.mynotes.domain.notes.models.Note

sealed class NoteListState {

    object Default : NoteListState()
    object Loading : NoteListState()
    class NotesProvided(val data: NoteListData) : NoteListState()
    class NoteProvided(val data: NoteData) : NoteListState()
    class Error(val message: String) : NoteListState()
}

class NoteListData(
    val notes: List<Note>,
)
class NoteData(
    val note: Note,
)
