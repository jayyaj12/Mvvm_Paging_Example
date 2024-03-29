package com.example.mvvmexample.data.repository

import androidx.paging.PagingData
import com.example.mvvmexample.ui.book.Book
import com.example.mvvmexample.ui.book.SearchBookDto
import kotlinx.coroutines.flow.Flow

interface BookRepository {

    // 책 검색하기 api 페이징 사용
    suspend fun getSearchBookPaging(
        query: String,
        sort: String?,
        page: Int?,
        size: Int?,
        target: String?,
    ): Flow<PagingData<Book>>


    // 책 검색하기 api
    suspend fun getSearchBook(
        query: String,
        sort: String? = null,
        page: Int? = null,
        size: Int? = null,
        target: String? = null,
    ): Result<SearchBookDto>

}