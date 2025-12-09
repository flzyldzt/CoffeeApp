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
    private val repo: FirebaseRepository
) : ViewModel() {

    private val _passwordState = MutableLiveData<Resource<Unit>>()
    val passwordState: LiveData<Resource<Unit>> = _passwordState

}