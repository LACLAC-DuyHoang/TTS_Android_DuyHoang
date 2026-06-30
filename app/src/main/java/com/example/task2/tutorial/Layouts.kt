package com.example.task2.tutorial

import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ColumnExample(){
    // colum xếp các phần tử theo chiều dọc
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp).background(Color(0xFFF3F4F6))
    ) {
        Text(text = "Item 1" , fontSize = 16.sp, color = Color.Black)
        Text(text = "Item 2", fontSize = 16.sp, color = Color.Blue)
        Text(text = "Item 3", fontSize = 16.sp, color = Color.Red)
    }
}
@Composable
fun RowExample(){
    // Row xếp các thành phần theo chiều nam
}