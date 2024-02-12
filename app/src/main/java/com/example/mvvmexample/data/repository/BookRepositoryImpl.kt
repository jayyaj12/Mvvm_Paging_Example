package com.example.mvvmexample.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mvvmexample.data.network.BookServiceApi
import com.example.mvvmexample.ui.book.Book
import com.example.mvvmexample.ui.book.BookSearchPagingSource
import com.example.mvvmexample.ui.book.mapperToBook
import com.example.mvvmexample.util.Constant.PAGING_MAX_SIZE
import com.example.mvvmexample.util.Constant.PAGING_SIZE
import com.example.mvvmexample.util.network.NetworkState
import com.example.mvvmexample.util.network.RetrofitFailureStateException
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(private val bookServiceApi: BookServiceApi) :
    BookRepository {
    override suspend fun getSearchBookPaging(
        token: String,
        query: String,
        sort: String?,
        page: Int?,
        size: Int?,
        target: String?
    ): Flow<PagingData<Book>> {
        val pagingSourceFactory = { BookSearchPagingSource(query, bookServiceApi) }

        return Pager(
            config = PagingConfig(
                pageSize = PAGING_SIZE,
                enablePlaceholders = false,
                maxSize = PAGING_MAX_SIZE
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override suspend fun getSearchBook(
        token: String,
        query: String,
        sort: String?,
        page: Int?,
        size: Int?,
        target: String?
    ): Result<List<Book>> {
        when(val searchBookList =
            bookServiceApi.getSearchBook(token, query, sort, page, size, target)) {
            is NetworkState.Success -> return Result.success(mapperToBook(searchBookList.body))
            is NetworkState.Failure -> return Result.failure(
                RetrofitFailureStateException(searchBookList.error, searchBookList.code)
            )
            is NetworkState.NetworkError -> Timber.tag("${this.javaClass.name}_getSearchBook").d(searchBookList.error)
            is NetworkState.UnknownError -> Timber.tag("${this.javaClass.name}_getSearchBook").d(searchBookList.t)
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }
}