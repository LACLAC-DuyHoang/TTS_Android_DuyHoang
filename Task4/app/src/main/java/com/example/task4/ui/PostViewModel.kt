package com.example.task4.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task4.data.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val repository = PostRepository()

    private val _uiState =
        MutableStateFlow<PostUiState>(PostUiState.Loading)

    val uiState: StateFlow<PostUiState> =
        _uiState.asStateFlow()

    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch {

            _uiState.value = PostUiState.Loading

            try {
                val posts = repository.getPosts()

                _uiState.value = PostUiState.Success(posts)

            } catch (e: Exception) {

                _uiState.value = PostUiState.Error(
                    e.message ?: "Không thể tải dữ liệu"
                )
            }
        }
    }
}