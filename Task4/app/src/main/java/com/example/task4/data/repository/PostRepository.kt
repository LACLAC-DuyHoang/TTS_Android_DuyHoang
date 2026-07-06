package com.example.task4.data.repository

import com.example.task4.data.api.RetrofitInstance
import com.example.task4.data.model.Post

class PostRepository {

    suspend fun getPosts(): List<Post> {
        return RetrofitInstance.api.getPosts()
    }
    //Repository là lớp trung gian giữa ViewModel và API.
    //Không nên để ViewModel gọi Retrofit trực tiếp.
    //ViewModel → Repository → Retrofit → API
}