package com.example.mvvmexample.data.network

import com.example.mvvmexample.ui.book.BookEntity
import com.example.mvvmexample.util.network.NetworkState
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BookServiceApi {
    // 페이징 사용
    @GET("search/book")
    suspend fun getSearchBookPaging(
        @Query("query") query: String,
        @Query("sort") sort: String?,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("target") target: String?,
    ): Response<BookEntity.GetSearchBookResponse>

    // 페이징 사용 x
    @GET("search/book")
    suspend fun getSearchBook(
        @Query("query") query: String,
        @Query("sort") sort: String? = null,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
        @Query("target") target: String? = null,
    ): NetworkState<BookEntity.GetSearchBookResponse>

}