package com.bav.mynotes.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import com.bav.mynotes.domain.notes.models.Note
import com.bav.mynotes.presenter.theme.CustomTheme
import com.bav.mynotes.presenter.theme.MainTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<NotesViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.loadNotes()

            MainTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = CustomTheme.colors.primaryBackground,
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = "Заметки",
                                    style = CustomTheme.typography.toolbar,
                                )
                            },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = CustomTheme.colors.primary,
                            ),
                        )
                    },
                    content = { paddingValues ->
                        NoteList(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                        )
                    },
                )
            }
        }
    }
}

@Composable
private fun NoteList(modifier: Modifier = Modifier, viewModel: NotesViewModel = koinViewModel()) {
    val state by viewModel.notesState.collectAsState()

    when (state) {
        is NoteListState.Default -> {
            Text(
                text = "Default",
                modifier = modifier,
            )
        }

        is NoteListState.Loading -> {
            CircularProgressIndicator()
        }

        is NoteListState.Error -> {
            Text(
                text = (state as NoteListState.Error).message,
                modifier = modifier,
            )
        }

        is NoteListState.NotesProvided -> {
            val list by remember {
                mutableStateOf((state as NoteListState.NotesProvided).data.notes)
            }
            Box(modifier = modifier) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(CustomTheme.shapes.padding),
                    verticalArrangement = Arrangement.spacedBy(CustomTheme.shapes.padding),
                ) {
                    items(
                        items = list,
                        key = { note -> note.id },
                    ) { note ->
                        NoteView(
                            note = note,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
            }
        }

        is NoteListState.NoteProvided -> {
            // TODO: navigation
        }
    }
}

@Composable
private fun NoteView(
    note: Note,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .defaultMinSize(minHeight = CustomTheme.shapes.minSize)
            .clip(shape = CustomTheme.shapes.cornerStyle)
            .background(color = CustomTheme.colors.primary)
            .padding(all = CustomTheme.shapes.padding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = note.title,
            style = CustomTheme.typography.body,
            modifier = Modifier
                .fillMaxWidth(),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
