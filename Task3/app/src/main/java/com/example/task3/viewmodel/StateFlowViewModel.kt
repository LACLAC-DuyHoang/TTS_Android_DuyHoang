package com.example.task3.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StateFlowViewModel : ViewModel(){
    // state có thể thay đổi( chỉ có ViewModel mới được sửa)
    private val _count = MutableStateFlow(0)
    // state chỉ đọc (UI chỉ được đọc)
    val count : StateFlow<Int> = _count.asStateFlow()// asStateFlow biến MutableStateFlow thành StateFlow
    fun increase(){
        _count.value++
    }
    fun decrease() {
        _count.value--
    }

    fun reset() {
        _count.value = 0
    }
}