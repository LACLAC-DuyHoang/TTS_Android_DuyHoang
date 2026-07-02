package com.example.task3.ui
import  android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
@Preview(showBackground = true)
@Composable
fun NavigationDemoApp() {
    //tạo 1 đối tượng quản lý điều hướng.
    val navController = rememberNavController()// rememberNavController giúp giữ lại controller khi recomposition
    //NavHost là “khung chứa các màn hình”
    NavHost(
        navController = navController,
        startDestination = "home" // khi mở app, màn hình đầu tiên là home
    ) {
        //Khai báo route "home" và UI tương ứng
        composable("home") {
            HomeScreen(navController)
        }

        composable("detail") {
            DetailScreen(navController)
        }

        composable("profile") {
            ProfileScreen(navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Trang chủ")
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Đây là màn hình Home",
                style = MaterialTheme.typography.headlineSmall
            )

            Button(
                onClick = {
                    navController.navigate("detail")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Đi đến Detail")
            }

            Button(
                onClick = {
                    navController.navigate("profile")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text("Đi đến Profile")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Chi tiết sản phẩm")
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Đây là màn hình Detail",
                style = MaterialTheme.typography.headlineSmall
            )

            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(top = 16.dp)
            ) {
                Text("Quay lại Home")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Hồ sơ cá nhân")
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Đây là màn hình Profile",
                style = MaterialTheme.typography.headlineSmall
            )

            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(top = 16.dp)
            ) {
                Text("Quay lại")
            }
        }
    }
}