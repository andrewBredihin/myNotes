package com.bav.mynotes.presenter.noteinfo

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.bav.mynotes.domain.notes.models.Note
import com.bav.mynotes.domain.notes.models.Task
import com.bav.mynotes.presenter.theme.CustomTheme
import org.koin.androidx.compose.koinViewModel

fun testFunDelete() {
    val a = 5
    // FIXME
}

fun testDelete() {
    // FIXME
}

@Composable
fun NoteInfoComposable(
    id: String,
    modifier: Modifier = Modifier,
    viewModel: NoteInfoViewModel = koinViewModel(),
) {
    val state by viewModel.noteInfoState.collectAsState()

    when (state) {
        is NoteInfoState.Default -> {
            viewModel.loadNoteById(id = id)
        }

        is NoteInfoState.Loading -> {
            CircularProgressIndicator(
                modifier = modifier,
                color = CustomTheme.colors.primary,
            )
        }

        is NoteInfoState.Error -> {
            Text(
                text = (state as NoteInfoState.Error).message,
                modifier = modifier,
            )
        }

        is NoteInfoState.NoteInfoProvided -> {
            TaskList(
                modifier = modifier,
                note = (state as NoteInfoState.NoteInfoProvided).data.note,
                viewModel = viewModel,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskList(
    modifier: Modifier = Modifier,
    note: Note,
    viewModel: NoteInfoViewModel = koinViewModel(),
) {
    val list by remember {
        mutableStateOf(note.tasks.sortedBy { it.done })
    }
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(CustomTheme.shapes.padding),
        verticalArrangement = Arrangement.spacedBy(CustomTheme.shapes.padding),
    ) {
        item {
            Button(
                onClick = { viewModel.addTaskToNote(note.id) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = CustomTheme.shapes.padding),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CustomTheme.colors.primary,
                ),
                shape = CustomTheme.shapes.cornerStyle,
            ) {
                Text(
                    text = "Добавить задачу",
                    style = CustomTheme.typography.heading,
                    modifier = Modifier
                        .padding(all = CustomTheme.shapes.padding),
                )
            }
        }
        items(
            items = list,
            key = { task -> task.id },
        ) { task ->
            val dismissState = rememberDismissState(
                confirmValueChange = {
                    it == DismissValue.DismissedToStart
                },
            )
            if (dismissState.isDismissed(direction = DismissDirection.EndToStart)) {
                viewModel.deleteTask(id = note.id, task = task)
            }

            SwipeToDismissBox(
                state = dismissState,
                backgroundContent = {
                    val backgroundColor by animateColorAsState(
                        when (dismissState.targetValue) {
                            DismissValue.DismissedToStart -> CustomTheme.colors.secondary
                            else -> Color.White
                        },
                        label = "",
                    )

                    val iconScale by animateFloatAsState(
                        targetValue = if (dismissState.targetValue == DismissValue.DismissedToStart) 1.3f else 0.5f,
                        label = "",
                    )

                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(color = backgroundColor)
                            .padding(end = CustomTheme.shapes.padding),
                        contentAlignment = Alignment.CenterEnd,
                    ) {
                        Icon(
                            modifier = Modifier.scale(iconScale),
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete",
                            tint = Color.White,
                        )
                    }
                },
                directions = setOf(DismissDirection.EndToStart),
            ) {
                TaskView(
                    task = task,
                    modifier = Modifier.fillMaxWidth(),
                    updateCallback = {
                        viewModel.updateNote(
                            id = note.id,
                            task = task.copy(done = !task.done),
                        )
                    },
                )
            }
        }
    }
}

@Composable
private fun TaskView(
    task: Task,
    modifier: Modifier = Modifier,
    updateCallback: () -> Unit,
) {
    Row(
        modifier = modifier
            .defaultMinSize(minHeight = CustomTheme.shapes.minSize)
            .clip(shape = CustomTheme.shapes.cornerStyle)
            .background(
                color = when (task.done) {
                    false -> CustomTheme.colors.primary
                    true -> CustomTheme.colors.secondary
                },
            )
            .padding(all = CustomTheme.shapes.padding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = task.content,
            style = CustomTheme.typography.body,
            modifier = Modifier
                .fillMaxWidth(0.9f),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        Checkbox(
            checked = task.done,
            onCheckedChange = { updateCallback.invoke() },
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = CustomTheme.shapes.cornerStyle),
            colors = CheckboxDefaults.colors(
                checkedColor = CustomTheme.colors.tintColor,
                uncheckedColor = CustomTheme.colors.tintColor,
                checkmarkColor = CustomTheme.colors.secondaryText,
            ),
        )
    }
}
