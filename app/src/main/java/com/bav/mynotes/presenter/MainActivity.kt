package com.bav.mynotes.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.bav.mynotes.presenter.theme.MyNotesTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<NotesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.loadNotes()

            MyNotesTheme {
                // A surface container using the 'background' color from the theme
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

        is NoteListState.DataProvided -> {
            Text(
                text = (state as NoteListState.DataProvided).data.notes.size.toString(),
                modifier = modifier,
            )
        }
    }
}
