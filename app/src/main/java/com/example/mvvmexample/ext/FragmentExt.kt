package com.example.mvvmexample.ext

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mvvmexample.R
import com.example.mvvmexample.ui.book.Book
import com.example.mvvmexample.ui.book.BookSearchFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun Fragment.onReplaceFragment(fragment: Fragment) {
    this.parentFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
        .commitAllowingStateLoss()
}