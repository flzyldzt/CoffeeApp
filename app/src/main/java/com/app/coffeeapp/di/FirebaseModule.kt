package com.app.coffeeapp.di

import com.app.coffeeapp.data.firebase.FirebaseRepository
import com.app.coffeeapp.data.firebase.FirebaseRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideFirebaseRepository(auth: FirebaseAuth): FirebaseRepository {
        return FirebaseRepositoryImpl(auth)
    }
}