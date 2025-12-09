package com.app.coffeeapp.ui.authentication.signup

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
class SignupViewModel @Inject constructor(
    private val repo: FirebaseRepository
) : ViewModel() {

    private val _registerState = MutableLiveData<Resource<FirebaseUser>>()
    val registerState: LiveData<Resource<FirebaseUser>> = _registerState

    fun register(email: String, password: String, birthDate: String) {
        viewModelScope.launch {
            _registerState.value = Resource.Loading()
            val res = repo.register(email, password, birthDate)
            if (res.isSuccess) _registerState.value = Resource.Success(res.getOrNull()!!)
            else _registerState.value = Resource.Error(res.exceptionOrNull()?.message ?: "Unknown")
        }
    }
}