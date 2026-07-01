package com.example.task3.ui

import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.task3.viewmodel.LoginViewModel
@Preview(backgroundColor = Color.WHITE.toLong())
@Composable
fun LonginScreen( vm: LoginViewModel = viewModel()){
   val message by vm.message.collectAsState()
    val loading by vm.loading.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text= message,
            style = MaterialTheme.typography.headlineLarge
        )
        if(loading){
            CircularProgressIndicator()
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                vm.Login()
            }
        ) {
            Text("Login")
        }

    }
}