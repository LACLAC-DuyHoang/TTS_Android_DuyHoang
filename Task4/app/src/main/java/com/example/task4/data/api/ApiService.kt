package com.example.task4.data.api

import com.example.task4.data.model.Post
import retrofit2.http.GET

interface ApiService{
    @GET("posts")//gọi endpoint  /posts
    //hàm chạy bất đồng bộ bằng Coroutine
    suspend  fun getPosts(): List<Post>
}