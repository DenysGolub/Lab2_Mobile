package com.example.lab1_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.lab1_navigation.ui.theme.Lab1_NavigationTheme
import com.example.lab1_navigation.view.ProfilePage
import com.example.lab1_navigation.view.SettingsPage
import com.example.lab1_navigation.viewmodel.ProfileViewModel
import com.example.lab1_navigation.viewmodel.SettingsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Lab1_NavigationTheme {
                Lab1NavigationApp()
            }
        }
    }

    @Composable
    fun Lab1NavigationApp() {
        var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.PROFILE) }

        NavigationSuiteScaffold(
            navigationSuiteItems = {
                AppDestinations.entries.forEach { destination ->
                    item(
                        icon = {
                            Icon(
                                destination.icon,
                                contentDescription = destination.label
                            )
                        },
                        label = { Text(destination.label) },
                        selected = destination == currentDestination,
                        onClick = { currentDestination = destination }
                    )
                }
            }
        ) {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                when (currentDestination) {
                    AppDestinations.PROFILE ->
                        ProfileScreen(Modifier.padding(innerPadding))
                    AppDestinations.SETTINGS ->
                        SettingsScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    fun ProfileScreen(modifier: Modifier = Modifier) {
        val profileViewModel: ProfileViewModel by viewModels()
        ProfilePage(modifier, profileViewModel)
    }

    @Composable
    fun SettingsScreen(modifier: Modifier = Modifier) {
        val settingsViewModel: SettingsViewModel by viewModels()
        SettingsPage(modifier, settingsViewModel)
    }
}

