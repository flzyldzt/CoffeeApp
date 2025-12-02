package com.app.coffeeapp.ui.authentication.authenticationmain

import androidx.lifecycle.ViewModel
import com.app.coffeeapp.data.firebase.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationMainViewModel @Inject constructor(
    private val repository: FirebaseRepository
) : ViewModel() {

    fun getCurrentUser() = repository.getCurrentUser()
}