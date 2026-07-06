package com.example.task3.viewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.task3.model.Product
@Preview(showBackground = true)
@Composable
fun ProductScreen(
    productViewModel: ProductViewModel = hiltViewModel()
    /*hiltViewModel() sẽ: yêu cầu Hilt tạo ProductViewModel
                           Hilt thấy constructor cần ProductRepository
                            Hilt tìm mapping trong RepositoryModule
                            Hilt tạo ProductRepositoryImpl
                            Hilt truyền repository vào ViewModel
                            Compose nhận ViewModel và hiển thị dữ liệu
    */
) {
    val products by productViewModel.products.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(products) { product ->
            ProductItem(product)
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier.padding(bottom = 12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Giá: ${product.price} VNĐ"
            )
        }
    }
}