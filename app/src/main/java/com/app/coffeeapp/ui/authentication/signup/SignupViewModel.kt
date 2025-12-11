package com.app.coffeeapp.ui.authentication.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.coffeeapp.data.firebase.FirebaseRepository
import com.app.coffeeapp.data.firebase.model.RegisteredUserInfoRequest
import com.app.coffeeapp.util.Resource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repo: FirebaseRepository
) : ViewModel() {

    private val _registerState = MutableLiveData<Resource<FirebaseUser>>(Resource.Idle())
    val registerState: LiveData<Resource<FirebaseUser>> = _registerState

    fun setRegisterState(state: Resource<FirebaseUser>) {
        _registerState.value = state
    }

    fun callRegisterService(email: String, password: String, birthDate: String) {
        viewModelScope.launch {
            _registerState.value = Resource.Loading()
            val request = RegisteredUserInfoRequest(
                name = "",
                surname = "",
                email = email,
                birthDate = birthDate,
                password = password
            )
            val res = repo.callRegisterServices(request)
            when {
                res.isSuccess -> {
                    _registerState.value = Resource.Success(res.getOrNull()!!)
                }

                res.isFailure -> {
                    _registerState.value =
                        Resource.Error(res.exceptionOrNull()?.message ?: "Unknown")
                }
            }
        }
    }

    fun isValidPassword(password: String): Boolean {
        val hasMinLenght = password.length >= 8
        val hasLetter = password.any { it.isLetter() }
        val hasDigit = password.any { it.isDigit() }
        return hasMinLenght && hasLetter && hasDigit
    }
}