package com.example.task4.ui

import com.example.task4.data.model.Post

sealed class PostUiState {

    data object Loading : PostUiState()

    data class Success(
        val posts: List<Post>
    ) : PostUiState()

    data class Error(
        val message: String
    ) : PostUiState()
}