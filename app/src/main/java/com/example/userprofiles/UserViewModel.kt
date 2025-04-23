package com.example.userprofiles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _fact = MutableStateFlow<String?>(null)
    val fact: StateFlow<String?> = _fact

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchRandomUser()
    }

    fun fetchRandomUser() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val response = RetrofitInstance.api.getRandomFact()
                _fact.value = response.results
            } catch (e: Exception) {
                _error.value = "Failed to load user: ${e.message}"
            }
            _loading.value = false
        }
    }
}
