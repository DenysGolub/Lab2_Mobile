package com.example.lab1_navigation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {

    private val _status = MutableLiveData("Налаштування ще не змінювались")
    val status: LiveData<String> = _status

    private val _isDarkMode = MutableLiveData(false)
    val isDarkMode: LiveData<Boolean> = _isDarkMode

    private val _language = MutableLiveData("Українська")
    val language: LiveData<String> = _language

    fun toggleTheme() {
        _isDarkMode.value = !_isDarkMode.value!!
        val modeText = if (_isDarkMode.value == true) "Темна тема активна" else "Світла тема активна"
        _status.value = modeText
    }

    fun changeLanguage() {
        _language.value = if (_language.value == "Українська") "English" else "Українська"
        _status.value = "Мову змінено на ${_language.value}"
    }

    fun resetSettings() {
        _isDarkMode.value = false
        _language.value = "Українська"
        _status.value = "Налаштування скинуто до стандартних"
    }
}
