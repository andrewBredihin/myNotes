package com.bav.mynotes.presenter.noteinfo

import com.bav.mynotes.domain.notes.models.Note

sealed class NoteInfoState {

    object Default : NoteInfoState()
    object Loading : NoteInfoState()
    class NoteInfoProvided(val data: NoteInfoData) : NoteInfoState()
    class Error(val message: String) : NoteInfoState()
}

class NoteInfoData(
    val note: Note,
)
