package com.example.task3.Repository

import com.example.task3.model.Product
// ViewModel chỉ cần biết:repository.getProducts(), không cần biết dữ liệu đến từ API, Room Database hay danh sách giả
interface ProductRepository {
    fun getProducts(): List<Product>
}