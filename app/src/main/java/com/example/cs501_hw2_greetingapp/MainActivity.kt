package com.example.cs501_hw2_greetingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingApp()
        }
    }
}

@Composable
fun GreetingApp() {
    var name by remember { mutableStateOf("") }
    var greeting by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Enter your name please") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            greeting = generateGreeting(name)
        }) {
            Text(text = "Get Greeting")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = greeting,
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

fun generateGreeting(name: String): String {
    val currentTime = LocalTime.now()
    val hour = currentTime.hour

    val timeOfDayGreeting = when {
        hour in 5..11 -> "good morning"
        hour in 12..17 -> "good afternoon"
        hour in 18..21 -> "good evening"
        else -> "nighty night"
    }

    return if (name.isNotBlank()) {
        "$timeOfDayGreeting, $name"
    } else {
        "hello there!"
    }
}
