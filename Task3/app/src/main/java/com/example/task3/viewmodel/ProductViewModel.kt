package com.example.task3.viewmodel

import androidx.lifecycle.ViewModel
import com.example.task3.Repository.ProductRepository
import com.example.task3.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel// báo với hilt rằng đây là ViewModel
// báo với hilt : khi tạo ProductViewModel, hãy tự tìm và truyền ProductRepository vào
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())

    val products: StateFlow<List<Product>> = _products.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        _products.value = repository.getProducts()
    }
}