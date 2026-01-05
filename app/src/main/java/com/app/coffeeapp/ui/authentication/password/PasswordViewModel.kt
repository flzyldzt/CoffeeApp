package com.app.coffeeapp.ui.authentication.password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.coffeeapp.data.firebase.FirebaseRepository
import com.app.coffeeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordViewModel @Inject constructor(
    private val repo: FirebaseRepository
) : ViewModel() {

    private val _passwordState = MutableLiveData<Resource<Unit>>()
    val passwordState: LiveData<Resource<Unit>> = _passwordState

    fun sendPasswordResetEmail(email: String) {
        viewModelScope.launch {
            _passwordState.value = Resource.Loading()
            val res = repo.sendPasswordResetEmail(email)
            when {
                res.isSuccess -> {
                    _passwordState.value = Resource.Success(Unit)
                }
                res.isFailure -> {
                    _passwordState.value = Resource.Error(
                        res.exceptionOrNull()?.message ?: "Bilinmeyen bir hata oluştu"
                    )
                }
            }
        }
    }
}