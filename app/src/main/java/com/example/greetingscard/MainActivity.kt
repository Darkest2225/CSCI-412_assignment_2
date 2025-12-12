package com.example.greetingscard

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {
    private val customPermission = "com.example.greetingscard.MSE412"
    private val requestCode = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (ContextCompat.checkSelfPermission(this, customPermission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(customPermission), requestCode)
        }

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
                            if (ContextCompat.checkSelfPermission(context, customPermission) == PackageManager.PERMISSION_GRANTED) {
                                val intent = Intent(context, SecondActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(context, "Permission required", Toast.LENGTH_SHORT).show()
                            }
                        }) {
                            Text("Start Activity Explicitly")
                        }
                        Button(onClick = {
                            if (ContextCompat.checkSelfPermission(context, customPermission) == PackageManager.PERMISSION_GRANTED) {
                                val intent = Intent("android.intent.action.SECOND")
                                startActivity(intent)
                            } else {
                                Toast.makeText(context, "Permission required", Toast.LENGTH_SHORT).show()
                            }
                        }) {
                            Text("Start Activity Implicitly")
                        }
                        Button(onClick = {
                            val intent = Intent(context, ThirdActivity::class.java)
                            startActivity(intent)
                        }) {
                            Text("View Image Activity")
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