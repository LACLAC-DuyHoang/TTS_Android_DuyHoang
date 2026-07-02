package com.example.task3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.task3.ui.LoginScreen
import com.example.task3.ui.ModifierLessonTheme

class Login : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModifierLessonTheme {
                LoginScreen()
            }
        }
    }
}