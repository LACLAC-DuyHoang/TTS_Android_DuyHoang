package com.example.task3.ui

import androidx.compose.runtime.Composable
import com.example.task3.viewmodel.StateFlowViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun StateFlowScreen(
    // khai báo 1 đối tượng CounterViewModel
    //Nếu người dùng không truyền ViewModel thì Compose sẽ tự lấy(hoặc tạo) một CounterViewModel
    stateFlowViewModel: StateFlowViewModel = viewModel()
    /* viewModel(): hàm của Jetpack Compose.
        viewModel<CounterViewModel>()
        Kiểm tra xem CounterViewModel đã tồn tại hay chưa
        Nếu đã có thì trả về đối tượng hiện tại
        Nếu chưa có thì tạo mới.
        Quản lý vòng đời (lifecycle) của ViewModel
    */
) {

    // Quan sát StateFlow
    val count by stateFlowViewModel.count.collectAsState()// chuyển StateFlow trong ViewModel thành State của Compose
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Counter",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "$count",
            style = MaterialTheme.typography.displayLarge
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                stateFlowViewModel.increase()
            }
        ) {
            Text("Increase")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                stateFlowViewModel.decrease()
            }
        ) {
            Text("Decrease")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                stateFlowViewModel.reset()
            }
        ) {
            Text("Reset")
        }

    }
}