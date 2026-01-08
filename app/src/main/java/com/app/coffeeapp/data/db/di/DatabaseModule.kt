package com.app.coffeeapp.data.db.di

import android.content.Context
import androidx.room.Room
import com.app.coffeeapp.data.db.AppDatabase
import com.app.coffeeapp.data.db.FavoriteProductRepository
import com.app.coffeeapp.data.db.FavoriteStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "coffee_app_database"
        ).build()
    }
    
    @Provides
    @Singleton
    fun provideFavoriteStoreDao(database: AppDatabase) = database.favoriteStoreDao()
    
    @Provides
    @Singleton
    fun provideFavoriteProductDao(database: AppDatabase) = database.favoriteProductDao()
    
    @Provides
    @Singleton
    fun provideFavoriteStoreRepository(
        favoriteStoreDao: com.app.coffeeapp.data.db.dao.FavoriteStoreDao
    ): FavoriteStoreRepository = FavoriteStoreRepository(favoriteStoreDao)
    
    @Provides
    @Singleton
    fun provideFavoriteProductRepository(
        favoriteProductDao: com.app.coffeeapp.data.db.dao.FavoriteProductDao
    ): FavoriteProductRepository = FavoriteProductRepository(favoriteProductDao)
}