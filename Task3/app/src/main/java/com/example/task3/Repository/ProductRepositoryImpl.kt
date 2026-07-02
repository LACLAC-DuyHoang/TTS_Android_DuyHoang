package com.example.task3.Repository

import com.example.task3.model.Product
import jakarta.inject.Inject
//@Inject constructor(): khi cần ProductRepositoryImpl, bạn có thể gọi constructor này để tạo object.
class ProductRepositoryImpl @Inject constructor() : ProductRepository {
    override fun getProducts(): List<Product> {
        return listOf(
            Product(1, "Laptop Dell", 25000000.0),
            Product(2, "Bàn phím cơ", 1200000.0),
            Product(3, "Chuột không dây", 500000.0)
        )
    }
}