package com.example.mvvmexample.ui.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mvvmexample.data.repository.BookRepository
import com.example.mvvmexample.util.authorization.GetAuthorization
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel @Inject constructor(private val bookRepository: BookRepository) : ViewModel() {

    // 질문 내용
    var query: String = "책"

    lateinit var clickBookItem: Book

    // 페이징 데이터
    private val _searchBookPagingResult = MutableStateFlow<PagingData<Book>>(PagingData.empty())
    val searchBookPagingResult = _searchBookPagingResult.asStateFlow()

    // 책 검색하기 페이징 사용
    fun getSearchBookPaging() {
        viewModelScope.launch {
            bookRepository.getSearchBookPaging(
                GetAuthorization.getAuthorizationToken(), query, null, null, null, null
            ).cachedIn(viewModelScope).collect {
                _searchBookPagingResult.value = it
            }
        }
    }

//     책 검색하기 페이징 사용 안함
    fun getSearchBook() {
        viewModelScope.launch {
            bookRepository.getSearchBook(
                GetAuthorization.getAuthorizationToken(), query, null, null, null, null
            ).onSuccess {
                // 성공
                Timber.e("it $it")
            }.onFailure {
                // 실패
                Timber.e("message ${it.message}")
            }
        }
    }

    fun queryTextChangedWatcher(str: CharSequence,  start: Int,  before: Int,  count: Int) {
        query = str.toString()
    }

    fun setClickedBookItem(item: Book) {
        clickBookItem = item
    }
}