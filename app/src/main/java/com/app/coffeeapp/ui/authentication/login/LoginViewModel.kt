package com.app.coffeeapp.ui.authentication.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.coffeeapp.data.firebase.FirebaseRepository
import com.app.coffeeapp.util.Resource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: FirebaseRepository
) : ViewModel() {

    private val _loginState = MutableLiveData<Resource<FirebaseUser>>()
    val loginState: LiveData<Resource<FirebaseUser>> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = Resource.Loading()
            val res = repo.login(email, password)
            if (res.isSuccess) _loginState.value = Resource.Success(res.getOrNull()!!)
            else _loginState.value = Resource.Error(res.exceptionOrNull()?.message ?: "Unknown")
        }
    }
}