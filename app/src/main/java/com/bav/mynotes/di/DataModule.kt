package com.bav.mynotes.di

import com.bav.mynotes.data.notes.repository.NoteRepositoryImpl
import com.bav.mynotes.data.notes.storage.NoteStorage
import com.bav.mynotes.data.notes.storage.test.TestStorage
import com.bav.mynotes.domain.notes.repository.NoteRepository
import org.koin.dsl.module

val dataModule = module {

    single<NoteRepository> { NoteRepositoryImpl(storage = get()) }

    single<NoteStorage> { TestStorage() }
}
