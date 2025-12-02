package com.app.coffeeapp.ui.authentication.password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.coffeeapp.data.firebase.FirebaseRepository
import com.app.coffeeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PasswordViewModel @Inject constructor(
    private val repository: FirebaseRepository
) : ViewModel() {

    private val _resetResult = MutableLiveData<Resource<Unit>>()
    val resetResult: LiveData<Resource<Unit>> = _resetResult

    fun resetPassword(email: String) {
        _resetResult.value = Resource.Loading
        repository.resetPassword(email) { result ->
            _resetResult.postValue(
                result.fold(
                    onSuccess = { Resource.Success(Unit) },
                    onFailure = { Resource.Error(it.message ?: "Unknown error") }
                )
            )
        }
    }
}