package com.example.mvvmexample.ui.book

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mvvmexample.R
import com.example.mvvmexample.databinding.FragmentBookBinding
import com.example.mvvmexample.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookSearchFragment : BaseFragment<FragmentBookBinding>(R.layout.fragment_book) {

    private val bookSearchViewModel: BookSearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initAfter() {
        binding.bookSearchFragment = this
        binding.bookSearchViewModel = bookSearchViewModel
    }
}