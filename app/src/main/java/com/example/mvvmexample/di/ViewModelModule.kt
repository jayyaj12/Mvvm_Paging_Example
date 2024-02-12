package com.example.mvvmexample.di

import com.example.mvvmexample.data.repository.BookRepository
import com.example.mvvmexample.ui.book.BookSearchViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Singleton
    @Provides
    fun provideBookViewModel(bookRepository: BookRepository) = BookSearchViewModel(bookRepository)

}