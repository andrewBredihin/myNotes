package com.bav.mynotes.di

import com.bav.mynotes.presenter.noteinfo.NoteInfoViewModel
import com.bav.mynotes.presenter.notes.NotesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        NotesViewModel(
            getNotesUseCase = get(),
            updateNoteUseCase = get(),
            deleteNoteUseCase = get(),
        )
    }
    viewModel {
        NoteInfoViewModel(
            getNoteByIdUseCase = get(),
            saveNoteUseCase = get(),
            updateNoteUseCase = get(),
        )
    }
}
