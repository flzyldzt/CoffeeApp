package com.app.coffeeapp.util

sealed class Resource<out T> { //işlemin durumunu yönetmek için kullanılır.
    object Loading : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
}