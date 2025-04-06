//package com.example.testapplication.viewmodel
//
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.testapplication.data.RetrofitInstance
//import com.example.testapplication.model.User
//import kotlinx.coroutines.launch
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.State
//
//class MainViewModel : ViewModel() {
//
//    private val _username = mutableStateOf("")
//    val username: State<String> = _username
//
//    private val _user = mutableStateOf<User?>(null)
//    val user: State<User?> = _user
//
//    private val _error = mutableStateOf("")
//    val error: State<String> = _error
//
//    fun onUsernameChange(newName: String) {
//        _username.value = newName
//    }
//
//
//
//    fun searchUser() {
//        viewModelScope.launch {
//            try {
//                val result = RetrofitInstance.api.getUser(_username.value)
//
//                // ✅ Print the full user object for debugging
//                println("Fetched user: $result")  // Or use Log.d for Logcat
//
//                _user.value = result
//                _error.value = ""
//            } catch (e: Exception) {
//                _user.value = null
//                _error.value = "User not found"
//
//                // ✅ Also print the error message
//                println("API Error: ${e.message}")
//            }
//        }
//    }
//}

//
//package com.example.testapplication.viewmodel
//
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.testapplication.data.RetrofitInstance
//import com.example.testapplication.model.User
//import kotlinx.coroutines.launch
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.State
//
//class MainViewModel : ViewModel() {
//
//    private val _username = mutableStateOf("")
//    val username: State<String> = _username
//
//    private val _user = mutableStateOf<User?>(null)
//    val user: State<User?> = _user
//
//    private val _error = mutableStateOf("")
//    val error: State<String> = _error
//
//    // ✅ Profile cache to avoid re-fetching same user repeatedly
//    private val profileCache = mutableMapOf<String, User>()
//
//    fun onUsernameChange(newName: String) {
//        _username.value = newName
//    }
//
//    fun searchUser() {
//        val username = _username.value.trim()
//
//        // ✅ Use cached profile if available
//        if (profileCache.containsKey(username)) {
//            _user.value = profileCache[username]
//            _error.value = ""
//            println("Loaded from cache: $username")
//            return
//        }
//
//        viewModelScope.launch {
//            try {
//                val result = RetrofitInstance.api.getUser(username)
//
//                // ✅ Print the full user object for debugging
//                println("Fetched user from API: $result")  // Or use Log.d for Logcat
//
//                _user.value = result
//                _error.value = ""
//
//                // ✅ Cache the result
//                profileCache[username] = result
//            } catch (e: Exception) {
//                _user.value = null
//                _error.value = "User not found"
//
//                // ✅ Also print the error message
//                println("API Error: ${e.message}")
//            }
//        }
//    }
//}


package com.example.testapplication.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.data.RetrofitInstance
import com.example.testapplication.model.User
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class MainViewModel : ViewModel() {

    private val _username = mutableStateOf("")
    val username: State<String> = _username

    private val _user = mutableStateOf<User?>(null)
    val user: State<User?> = _user

    private val _error = mutableStateOf("")
    val error: State<String> = _error

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> = _loading

    // ✅ Profile cache to avoid re-fetching same user repeatedly
    private val profileCache = mutableMapOf<String, User>()

    fun onUsernameChange(newName: String) {
        _username.value = newName
    }

    fun searchUser() {
        val username = _username.value.trim()

        _loading.value = true

        // ✅ Use cached profile if available
        if (profileCache.containsKey(username)) {
            _user.value = profileCache[username]
            _error.value = ""
            println("Loaded from cache: $username")
            _loading.value = false
            return
        }

        viewModelScope.launch {
            try {
                val result = RetrofitInstance.api.getUser(username)

                // ✅ Print the full user object for debugging
                println("Fetched user from API: $result")  // Or use Log.d for Logcat

                _user.value = result
                _error.value = ""

                // ✅ Cache the result
                profileCache[username] = result
            } catch (e: Exception) {
                _user.value = null
                _error.value = "User not found"

                // ✅ Also print the error message
                println("API Error: ${e.message}")
            } finally {
                _loading.value = false
            }
        }
    }
}
