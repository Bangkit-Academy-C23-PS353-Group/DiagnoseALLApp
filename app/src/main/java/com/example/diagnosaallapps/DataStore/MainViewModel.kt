package com.example.diagnosaallapps.DataStore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val pref: UserPreferences) : ViewModel() {
    fun getuser(): LiveData<UserViewModel> {
        return pref.getUser().asLiveData()
    }
    fun saveuser(user: UserViewModel) {
        viewModelScope.launch {
            pref.saveUser(user)
        }
    }
    fun logout() {
        viewModelScope.launch {
            pref.ClearUser()
        }
    }
}