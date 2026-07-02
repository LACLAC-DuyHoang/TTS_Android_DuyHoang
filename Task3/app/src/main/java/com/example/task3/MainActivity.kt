package com.example.task3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.task3.ui.LoginScreen
import com.example.task3.ui.ModifierLessonTheme
import com.example.task3.ui.NavigationDemoApp
import com.example.task3.ui.StateFlowScreen
import com.example.task3.viewmodel.ProductScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint//Activity này được phép sử dụng dependency do Hilt quản lý.
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        enableEdgeToEdge()
//
//        setContent {
//            NavigationDemoApp()
//        }
        setContent {
            ProductScreen()
        }
    }

}

