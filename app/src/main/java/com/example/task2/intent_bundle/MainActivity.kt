package com.example.task2.intent_bundle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.task2.SecondActivity
import com.example.task2.ui.theme.Task2Theme
import kotlin.jvm.java

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Button(
                onClick = {
                    //intent
                    val intent = Intent(
                        this,
                        SecondActivity::class.java
                    )//Ở đây android biết chắc chắn cần phải mở SecondActivity
                    intent.putExtra("name", "Hoàng") // truyền dữ liệu vào intent
                    intent.putExtra("age", 22)
                    startActivity(intent)
                }
            )
            { Text("Open") }
        }
        //Bundle
        val bundle = Bundle()
        bundle.putString("name", "Hoàng") // truyền dữ liệu vào bundle
        bundle.putInt("age", 22)
        intent.putExtras(bundle)// truyền dữ liệu trong bundle vào intent
    }
}

