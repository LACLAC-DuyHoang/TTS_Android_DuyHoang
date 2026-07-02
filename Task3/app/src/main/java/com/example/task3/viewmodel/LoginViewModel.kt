package com.example.task3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task3.ui.StateFlowScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(){
    // UI state
    private val _message = MutableStateFlow("Chưa đăng nhập")
    val message = _message.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()
    fun Login(){
        viewModelScope.launch {
            //Hiển thị loading
            _loading.value = true
            _message.value = "Đang đăng nhập ..."
            // giả lập gọi API
            delay(3000)
            //API trả về
            _loading.value = false
            _message.value = "Đăng nhập thành công"
        }
    }
}