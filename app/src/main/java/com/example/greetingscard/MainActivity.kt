package com.example.greetingscard

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greetingscard.ui.theme.GreetingsCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingsCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        Greeting(
                            name = "Donny Schroeder",
                            id = "1460575",
                            modifier = Modifier.padding(innerPadding)
                        )
                        val context = LocalContext.current
                        Button(onClick = {
                            val intent = Intent(context, SecondActivity::class.java)
                            startActivity(intent)
                        }) {
                            Text("Start Activity Explicitly")
                        }
                        Button(onClick = {
                            val intent = Intent("android.intent.action.SECOND")
                            startActivity(intent)
                        }) {
                            Text("Start Activity Implicitly")
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, id: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hi, my name is $name!" +
                "\n My id is $id",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreetingsCardTheme {
        Greeting("Donny Schroeder","1460575")
    }
}