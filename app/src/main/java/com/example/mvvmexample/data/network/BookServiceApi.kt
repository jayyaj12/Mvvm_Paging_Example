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
        @Header("Authorization") token: String,
        @Query("query") query: String,
        @Query("sort") sort: String?,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("target") target: String?,
    ): Response<BookEntity.GetSearchBookResponse>

    // 페이징 사용 x
    @GET("search/book")
    suspend fun getSearchBook(
        @Header("Authorization") token: String,
        @Query("query") query: String,
        @Query("sort") sort: String?,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("target") target: String?,
    ): NetworkState<BookEntity.GetSearchBookResponse>

}