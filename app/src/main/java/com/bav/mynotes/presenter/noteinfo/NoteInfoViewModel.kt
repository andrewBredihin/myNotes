package com.bav.mynotes.presenter.noteinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bav.mynotes.domain.notes.models.Task
import com.bav.mynotes.domain.notes.usecase.GetNoteByIdUseCase
import com.bav.mynotes.domain.notes.usecase.SaveNoteUseCase
import com.bav.mynotes.domain.notes.usecase.UpdateNoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteInfoViewModel(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
) : ViewModel() {

    private val _noteInfoState = MutableStateFlow<NoteInfoState>(NoteInfoState.Default)
    val noteInfoState = _noteInfoState.asStateFlow()

    fun loadNoteById(id: String) {
        _noteInfoState.value = NoteInfoState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            delay(100)
            try {
                val data = NoteInfoData(note = getNoteByIdUseCase.execute(id))
                withContext(Dispatchers.Main) {
                    _noteInfoState.value = NoteInfoState.NoteInfoProvided(data)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _noteInfoState.value = NoteInfoState.Error(e.stackTrace.contentDeepToString())
                }
            }
        }
    }

    fun updateNote(id: String, tasks: List<Task>) {
        _noteInfoState.value = NoteInfoState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var note = getNoteByIdUseCase.execute(id)
                note = note.copy(tasks = tasks)
                updateNoteUseCase.execute(note = note)
                withContext(Dispatchers.Main) {
                    loadNoteById(id = id)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _noteInfoState.value = NoteInfoState.Error(e.stackTrace.contentDeepToString())
                }
            }
        }
    }

    fun updateNote(id: String, task: Task) {
        _noteInfoState.value = NoteInfoState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var note = getNoteByIdUseCase.execute(id)
                var tasks = note.tasks
                tasks = replaceOrAdd(task, tasks)
                note = note.copy(tasks = tasks)
                updateNoteUseCase.execute(note = note)
                withContext(Dispatchers.Main) {
                    loadNoteById(id = id)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _noteInfoState.value = NoteInfoState.Error(e.stackTrace.contentDeepToString())
                }
            }
        }
    }

    fun deleteTask(id: String, task: Task) {
        _noteInfoState.value = NoteInfoState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var note = getNoteByIdUseCase.execute(id)
                var tasks = note.tasks
                tasks = tasks.toMutableList().apply {
                    remove(task)
                }.toList()
                note = note.copy(tasks = tasks)
                updateNoteUseCase.execute(note = note)
                withContext(Dispatchers.Main) {
                    loadNoteById(id = id)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _noteInfoState.value = NoteInfoState.Error(e.stackTrace.contentDeepToString())
                }
            }
        }
    }

    fun addTaskToNote(id: String) {
        _noteInfoState.value = NoteInfoState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var note = getNoteByIdUseCase.execute(id)
                var tasks = note.tasks
                val last = tasks.lastOrNull()
                if (last == null) {
                    tasks = listOf(
                        Task(
                            id = "0",
                            content = "",
                        ),
                    )
                } else {
                    tasks = tasks.toMutableList().apply {
                        add(
                            Task(
                                id = (last.id.toInt() + 1).toString(),
                                content = "",
                            ),
                        )
                    }.toList()
                }
                note = note.copy(tasks = tasks)
                updateNoteUseCase.execute(note = note)
                withContext(Dispatchers.Main) {
                    loadNoteById(id = id)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _noteInfoState.value = NoteInfoState.Error(e.stackTrace.contentDeepToString())
                }
            }
        }
    }

    private fun replaceOrAdd(task: Task, tasks: List<Task>): List<Task> {
        val replace = tasks.firstOrNull { it.id == task.id }
        return if (replace == null) {
            mutableListOf<Task>().apply {
                addAll(tasks)
                add(task)
            }.toList()
        } else {
            tasks.map {
                if (it.id == task.id) task else it
            }
        }
    }
}
