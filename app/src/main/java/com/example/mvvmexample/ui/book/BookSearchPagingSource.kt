package com.example.mvvmexample.ui.book

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mvvmexample.data.network.BookServiceApi
import com.example.mvvmexample.data.repository.BookRepository
import com.example.mvvmexample.util.Constant.PAGING_MAX_SIZE
import com.example.mvvmexample.util.Constant.START_PAGING_INDEX
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class BookSearchPagingSource(
    private val query: String,
    private val api: BookServiceApi
) : PagingSource<Int, Book>() {

    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        return try {
            val pageNumber = params.key ?: START_PAGING_INDEX

            val response = api.getSearchBookPaging(
                query = query,
                sort = null,
                page = pageNumber,
                size = params.loadSize,
                target = null
            )
            val endOfPaginationReached = response.body()?.meta?.isEnd!!
            val data = mapperToBook(response.body())

            val prevKey = if (pageNumber == START_PAGING_INDEX) null else pageNumber - 1
            val nextKey = if(endOfPaginationReached) {
                null
            } else {
                pageNumber + (params.loadSize / PAGING_MAX_SIZE) + 1
            }

            LoadResult.Page(
                data = data.item,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}