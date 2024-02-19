package com.example.mvvmexample.ui.book

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mvvmexample.R
import com.example.mvvmexample.databinding.FragmentBookBinding
import com.example.mvvmexample.ext.addFragment
import com.example.mvvmexample.ext.onReplaceFragment
import com.example.mvvmexample.ui.base.BaseFragment
import com.example.mvvmexample.ui.book.BookConstant.BOOK_REQUEST_KEY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class BookSearchFragment : BaseFragment<FragmentBookBinding>(R.layout.fragment_book) {

    val bookRequestCode: String by lazy {
        BOOK_REQUEST_KEY
    }
    private val bookSearchViewModel: BookSearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun initAfter() {
        binding.bookSearchFragment = this
        binding.bookSearchViewModel = bookSearchViewModel
        binding.bookListAdapter = BookSearchListAdapter {
            Timber.e("item $it")
            this@BookSearchFragment.onReplaceFragment(BookDetailFragment(), bookRequestCode, it, true)
        }
    }

    private fun setupViewModelObserver() {
        lifecycleScope.launch {
            bookSearchViewModel.searchBookCall.collectLatest {
//                bookSearchViewModel.getSearchBook()
            }
        }
    }
}