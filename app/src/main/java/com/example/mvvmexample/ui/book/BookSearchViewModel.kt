package com.example.mvvmexample.ui.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mvvmexample.data.repository.BookRepository
import com.example.mvvmexample.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel @Inject constructor(private val bookRepository: BookRepository) :
    BaseViewModel() {

    // 질문 내용
    var query: MutableLiveData<String> = MutableLiveData("")
    // 이전 질문 내용
    var tempQuery = ""
    // BookSearch 값이 마지막인지 여부
    private var isBookSearchLast = false
    private val searchBookList = arrayListOf<Book>()
    private var searchPage = 1

    // 페이징 데이터
    private val _searchBookPagingResult = MutableStateFlow<PagingData<Book>>(PagingData.empty())
    val searchBookPagingResult = _searchBookPagingResult.asStateFlow()

    // 페이징 데이터
    private val _searchBookResult = MutableLiveData<List<Book>>(emptyList())
    val searchBookResult: LiveData<List<Book>> get() = _searchBookResult

    // 페이징 데이터
    val _searchBookCall = MutableStateFlow<Boolean>(false)
    val searchBookCall: StateFlow<Boolean> get() = _searchBookCall



    // 책 검색하기 페이징 사용
    fun getSearchBookPaging() {
        viewModelScope.launch {
            bookRepository.getSearchBookPaging(
                query.value ?: "", null, null, null, null
            ).cachedIn(viewModelScope).collect {
                _searchBookPagingResult.value = it
            }
        }
    }

    // 책 검색하기 페이징 사용 안함
    fun getSearchBook(entryPoint: String) {
        // 다른 검색어 조회 시 search 관련 값 초기화
        if(entryPoint != "scroll" && query.value != tempQuery) {
            isBookSearchLast = false
            searchPage = 1
            searchBookList.clear()
        }

        // isLast 인지 체크
        if(!isBookSearchLast) {
            // 검색 정보 임시 데이터에 저장
            // 무한 스크롤 중 사용자가 검색어를 수정할 수 있기 때문에, scroll 시에는 기존 데이터를, 검색 버튼 클릭 시에는 tempQuery를 갱신한 후 query 값을 return 한다.
            val queryParam = when(entryPoint) {
                "button" -> {
                    tempQuery = query.value ?: ""
                    query.value
                }
                "scroll" -> tempQuery
                else -> ""
            }

            viewModelScope.launch {
                bookRepository.getSearchBook(queryParam ?: tempQuery, null, searchPage).onSuccess {
                    isBookSearchLast = it.isLast
                    searchBookList.addAll(it.item)
                    searchPage += 1
                    _searchBookResult.value = searchBookList
                }.onFailure {
                    // 실패
                    searchBookList.clear()
                    searchPage = 1
                }
            }
        } else {
            searchBookList.clear()
            searchPage = 1
        }
    }
}