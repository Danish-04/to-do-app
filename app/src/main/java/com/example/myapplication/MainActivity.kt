package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import javax.net.ssl.HandshakeCompletedEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ToDoApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoApp() {
    var taskText by rememberSaveable { mutableStateOf("") }
    var tasks = remember { mutableStateListOf<ToDo>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "To-Do List",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 50.dp)
        )

        TextField(
            value = taskText, onValueChange = {
                taskText = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            singleLine = false,
            textStyle = TextStyle(fontSize = 20.sp)
        )
        Button(
            onClick = {
                if (taskText.isNotEmpty()) {
                    tasks.add(ToDo(taskText))
                    taskText = ""
                }
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add Task")
        }

        Button(
            onClick = {
                tasks.removeAll { it.isCompleted }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Clear Completed Tasks")
        }
        LazyColumn(
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            itemsIndexed(tasks) { index, todo ->




                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = MaterialTheme.shapes.small
                        )

                ) {
                    Checkbox(
                        checked = todo.isCompleted,
                        onCheckedChange = {
                            tasks[index] = todo.copy(isCompleted=it)
                        }
                    )


                    Text(
                        text = todo.task,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }

        }

    }
}

data class ToDo(
    val task: String,
    val isCompleted: Boolean = false
)





