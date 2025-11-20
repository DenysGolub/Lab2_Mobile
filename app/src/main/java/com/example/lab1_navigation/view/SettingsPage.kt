package com.example.lab1_navigation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab1_navigation.viewmodel.SettingsViewModel

sealed class SettingsRoutes(val route: String) {
    object Main : SettingsRoutes("main")
    object Theme : SettingsRoutes("theme")
    object Language : SettingsRoutes("language")
}

@Composable
fun SettingsPage(modifier: Modifier, viewModel: SettingsViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SettingsRoutes.Main.route
    ) {
        composable(SettingsRoutes.Main.route) {
            MainSettingsScreen(
                viewModel = viewModel,
                onThemeClick = { navController.navigate(SettingsRoutes.Theme.route) },
                onLanguageClick = { navController.navigate(SettingsRoutes.Language.route) }
            )
        }

        composable(SettingsRoutes.Theme.route) {
            ThemeScreen(
                isDarkMode = viewModel.isDarkMode.observeAsState(false).value,
                onBack = { navController.popBackStack() }
            )
        }

        composable(SettingsRoutes.Language.route) {
            LanguageScreen(
                language = viewModel.language.observeAsState("Українська").value,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun MainSettingsScreen(viewModel: SettingsViewModel, onThemeClick: () -> Unit, onLanguageClick: () -> Unit) {
    val status by viewModel.status.observeAsState("Налаштування ще не змінювались")
    val isDarkMode by viewModel.isDarkMode.observeAsState(false)
    val language by viewModel.language.observeAsState("Українська")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isDarkMode) Color.DarkGray else Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Налаштування",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = if (isDarkMode) Color.White else Color(0xFF0D47A1)
        )

        Spacer(modifier = Modifier.height(20.dp))


        Button(
            onClick = onThemeClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Змінити тему", color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onLanguageClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Змінити мову", color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { viewModel.resetSettings() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB71C1C)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Скинути", color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = status,
            fontSize = 16.sp,
            color = if (isDarkMode) Color.LightGray else Color.DarkGray
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeScreen(onBack: () -> Unit, isDarkMode: Boolean) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Назад",
                            tint = if (isDarkMode) Color.White else Color.Black
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "Темна тема",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Увімкнути темну тему")
                    Spacer(modifier = Modifier.width(10.dp))
                    Switch(checked = isDarkMode, onCheckedChange = {})
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageScreen(onBack: () -> Unit, language: String) {
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "Мова застосунку",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(onClick = {}) {
                        Text("Українська")
                    }
                    Button(onClick = {}) {
                        Text("English")
                    }
                }
            }
        }
    )
}
