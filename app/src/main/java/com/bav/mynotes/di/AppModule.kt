package com.bav.mynotes.di

import com.bav.mynotes.presenter.NotesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        NotesViewModel(
            getNotesUseCase = get(),
            getNoteByIdUseCase = get(),
            saveNoteUseCase = get(),
        )
    }
}
