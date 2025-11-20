package com.example.lab1_navigation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab1_navigation.model.Account
import com.example.lab1_navigation.model.AccountInfo
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {
    val AccountInfo: AccountInfo = AccountInfo()

    private val _account = MutableLiveData<Account>()

    val account: LiveData<Account> = _account

    fun getAccount() {
        viewModelScope.launch {
            val userResult = AccountInfo.fetchAccountInfo()
            _account.postValue(userResult)
        }
    }
}