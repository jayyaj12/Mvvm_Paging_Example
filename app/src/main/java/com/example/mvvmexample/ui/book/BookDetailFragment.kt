package com.example.mvvmexample.ui.book

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.example.mvvmexample.R
import com.example.mvvmexample.databinding.FragmentBookBinding
import com.example.mvvmexample.databinding.FragmentBookDetailBinding
import com.example.mvvmexample.ext.onReplaceFragment
import com.example.mvvmexample.ext.popFragment
import com.example.mvvmexample.ui.base.BaseFragment
import com.example.mvvmexample.ui.book.BookConstant.BOOK_REQUEST_KEY
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.security.Key

@AndroidEntryPoint
class BookDetailFragment: BaseFragment<FragmentBookDetailBinding>(R.layout.fragment_book_detail) {

    var item: Book? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(BOOK_REQUEST_KEY) { requestKey, bundle ->
            item = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable<Book>("value", Book::class.java)
            } else {
                bundle.getParcelable("value")
            }
            Timber.e("item $item")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initAfter() {
        binding.bookDetailFragment = this@BookDetailFragment
    }
}