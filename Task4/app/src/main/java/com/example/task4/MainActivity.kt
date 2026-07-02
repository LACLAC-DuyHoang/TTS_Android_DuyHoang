package com.example.task4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.task4.ui.PostScreen
import com.example.task4.ui.theme.Task4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Task4Theme {
                PostScreen()
            }
        }
    }
}
/*
        MainActivity
             ↓
        PostScreen
             ↓
    PostViewModel được tạo
             ↓
      init { getPosts() }
             ↓
    viewModelScope.launch
            ↓
    PostRepository.getPosts()
            ↓
    Retrofit gọi API
             ↓
    Server trả JSON
            ↓
    Gson chuyển JSON thành List<Post>
            ↓
    StateFlow đổi thành Success
            ↓
    Compose tự cập nhật danh sách bài viết
* */
