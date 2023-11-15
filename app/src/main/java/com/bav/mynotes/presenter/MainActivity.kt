package com.bav.mynotes.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.loadNotes()

            MainTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier, viewModel: NotesViewModel = koinViewModel()) {
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
            Box {
                NoteView(
                    note = list[0],
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        is NoteListState.NoteProvided -> {
            // TODO: navigation
        }
    }
}

@Composable
fun NoteView(
    note: Note,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .defaultMinSize(CustomTheme.shapes.minSize)
            .padding(horizontal = CustomTheme.shapes.padding)
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
