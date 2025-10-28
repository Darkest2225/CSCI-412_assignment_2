package com.example.greetingscard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.greetingscard.ui.theme.GreetingsCardTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingsCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Software Engineering Challenges",
            modifier = modifier
        )
        Text(
            text = "1) Rapid Changes \n" +
                    "2) Unstable Environment \n" +
                    "3) OS Fragmentation \n" +
                    "4) Tool Support \n" +
                    "5) Low Tolerance"
        )
        val context = LocalContext.current
        Button(onClick = {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Main Activity")
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    GreetingsCardTheme {
        Greeting2("Android")
    }
}