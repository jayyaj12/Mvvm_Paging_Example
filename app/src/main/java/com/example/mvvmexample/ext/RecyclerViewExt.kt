package com.example.mvvmexample.ext

import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmexample.ui.book.Book
import com.example.mvvmexample.ui.book.BookDetailFragment
import com.example.mvvmexample.ui.book.BookSearchFragment
import com.example.mvvmexample.ui.book.BookSearchPagingAdapter
import com.example.mvvmexample.ui.book.BookSearchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@BindingAdapter("setFragment", "moveFragment", "requestKey", "setItem")
fun RecyclerView.bindSetPagingItems(
    fragment: Fragment,
    moveFragment: Fragment,
    requestKey: String,
    items: PagingData<Book>
) {
    val adapter = if (this.adapter == null) {
        BookSearchPagingAdapter {
            fragment.onReplaceFragment(moveFragment, requestKey, it, true)
        }
    } else {
        this.adapter as BookSearchPagingAdapter
    }

    if (this.adapter == null) {
        setHasFixedSize(true)

        this.adapter = adapter
        this.addItemDecoration(
            DividerItemDecoration(
                this.context, DividerItemDecoration.VERTICAL
            )
        )
    }

    CoroutineScope(Dispatchers.IO).launch {
        items.let {
            adapter.submitData(it)
        }
    }
}
