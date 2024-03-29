package com.example.mvvmexample.di

import com.example.mvvmexample.data.repository.BookRepository
import com.example.mvvmexample.data.repository.BookRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindBookRepository(bookRepositoryImpl: BookRepositoryImpl): BookRepository
}


