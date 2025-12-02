package com.app.coffeeapp.ui.authentication.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.coffeeapp.data.firebase.FirebaseRepository
import com.app.coffeeapp.util.Resource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: FirebaseRepository
) : ViewModel() {

    private val _loginResult = MutableLiveData<Resource<FirebaseUser>>()
    val loginResult: LiveData<Resource<FirebaseUser>> = _loginResult

    fun login(email: String, password: String) {
        _loginResult.value = Resource.Loading
        repository.login(email, password) { result ->
            _loginResult.postValue(
                result.fold(
                    onSuccess = { Resource.Success(it) },
                    onFailure = { Resource.Error(it.message ?: "Unknown error") }
                )
            )
        }
    }
}