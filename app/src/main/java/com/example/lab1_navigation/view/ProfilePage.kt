package com.example.lab1_navigation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab1_navigation.viewmodel.ProfileViewModel

@Composable
fun ProfilePage(modifier: Modifier = Modifier, viewModel: ProfileViewModel) {
    val account by viewModel.account.observeAsState()
    var statusText by remember { mutableStateOf("Профіль ще не завантажено") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE8EAF6))
            ,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Профіль користувача",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3F51B5)
        )

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, RoundedCornerShape(20.dp)),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(24.dp),
                horizontalAlignment = Alignment.Start
            ) {
                if (account == null) {
                    Text(
                        text = "Дані акаунта ще не завантажені.",
                        color = Color.Gray,
                        fontSize = 18.sp
                    )
                } else {
                    Text("Ім’я: ${account!!.name}", fontSize = 18.sp)
                    Text("Логін: ${account!!.username}", fontSize = 18.sp)
                    Text("Робота: ${account!!.job}", fontSize = 18.sp)
                }
            }
        }

        Text(
            text = statusText,
            fontSize = 18.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)),
            onClick = {
                viewModel.getAccount()
                statusText = if (account == null)
                    "Завантаження даних..."
                else
                    "Дані оновлено для користувача ${account!!.name}"
            }
        ) {
            Text("Оновити профіль", color = Color.White, fontSize = 18.sp)
        }
    }
}
