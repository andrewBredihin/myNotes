package com.bav.mynotes.di

import com.bav.mynotes.domain.notes.usecase.GetNoteByIdUseCase
import com.bav.mynotes.domain.notes.usecase.GetNotesUseCase
import com.bav.mynotes.domain.notes.usecase.SaveNoteUseCase
import com.bav.mynotes.domain.notes.usecase.UpdateNoteUseCase
import org.koin.dsl.module

val domainModule = module {

    factory { GetNotesUseCase(repository = get()) }

    factory { GetNoteByIdUseCase(repository = get()) }

    factory { SaveNoteUseCase(repository = get()) }

    factory { UpdateNoteUseCase(repository = get()) }
}
