package com.example.mvvmexample.ext

import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mvvmexample.R
import com.example.mvvmexample.ui.book.Book
import com.example.mvvmexample.ui.book.BookSearchFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

fun Fragment.onReplaceFragment(
    fragment: Fragment,
    requestKey: String = "",
    data: Any? = null,
    addBackStack: Boolean = false
) {
    this.parentFragmentManager.beginTransaction().apply {
        if (requestKey != "") {
            val bundle = Bundle()
            data?.let {
                bundle.putAny("value", data)
                setFragmentResult(requestKey, bundle)
            }
            replace(R.id.fragment_container, fragment)
            if (addBackStack) addToBackStack(fragment.tag)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            commit()
        }
    }
}

fun Fragment.popFragment() {
    Timber.e("this.parentFragmentManager.backStackEntryCount ${this.parentFragmentManager.backStackEntryCount}")
    if(this.parentFragmentManager.backStackEntryCount == 0) {
        requireActivity().finish()
    } else {
        this.parentFragmentManager.popBackStack()
    }
}