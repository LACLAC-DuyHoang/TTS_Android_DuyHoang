package com.example.task3.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.task3.R

@Preview(showBackground = true)

@Composable
fun ProfileScreen() {
    // Surface là lớp nền của toàn màn hình.
    // background lấy từ Theme nên tự phù hợp với Light/Dark mode.
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),

            // Căn các phần tử con theo chiều ngang vào giữa màn hình.
            horizontalAlignment = Alignment.CenterHorizontally,

            // Các phần tử bắt đầu từ phía trên.
            verticalArrangement = Arrangement.Top
        ) {

            // Surface nhỏ để tạo vùng chứa avatar.
            // primaryContainer là màu nền nhấn nhẹ lấy từ Theme.
            Surface(
                shape = MaterialTheme.shapes.extraLarge,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Avatar",

                    // padding tạo khoảng cách giữa ảnh và Surface bên ngoài.
                    modifier = Modifier
                        .size(120.dp)
                        .padding(12.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Hoàng Đỗ",

                // typography.headlineSmall: kiểu chữ tiêu đề đã định nghĩa trong Theme.
                style = MaterialTheme.typography.headlineSmall,

                // onBackground: màu chữ phù hợp khi đặt trên background.
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Android Developer",

                // bodyMedium: kiểu chữ nội dung thông thường.
                style = MaterialTheme.typography.bodyMedium,

                // onSurfaceVariant: màu chữ phụ, thường nhạt hơn tiêu đề.
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Card giúp nhóm các nút thao tác lại với nhau.
            Card(
                modifier = Modifier.fillMaxWidth(),

                // medium là mức bo góc được khai báo trong Theme.
                shape = MaterialTheme.shapes.medium,

                // surfaceVariant là màu nền phụ lấy từ Theme.
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),

                    // Các nút cách nhau 12dp.
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = {},

                        // weight(1f) giúp hai nút có chiều rộng bằng nhau.
                        modifier = Modifier.weight(1f)
                    ) {
                        // Button tự dùng primary làm nền
                        // và onPrimary làm màu chữ theo Theme.
                        Text("Follow")
                    }

                    FilledTonalButton(
                        onClick = {},
                        modifier = Modifier.weight(1f)

                        // FilledTonalButton thường dùng secondaryContainer/
                        // primaryContainer để tạo nút phụ nhẹ hơn Button.
                    ) {
                        Text("Message")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Surface này dùng để hiển thị thông tin phụ.
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Text(
                    text = "Đang học Jetpack Compose + Material3",
                    modifier = Modifier.padding(16.dp),

                    // bodyLarge: chữ nội dung lớn hơn bodyMedium.
                    style = MaterialTheme.typography.bodyLarge,

                    // onSecondaryContainer: màu chữ phù hợp trên secondaryContainer.
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}

