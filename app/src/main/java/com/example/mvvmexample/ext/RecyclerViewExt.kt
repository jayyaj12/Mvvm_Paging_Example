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

@BindingAdapter("setFragment","setBookSearchViewModel", "setItem")
fun RecyclerView.bindSetPagingItems(fragment: BookSearchFragment, viewModel: BookSearchViewModel, items: PagingData<Book>) {
    val adapter = if(this.adapter == null) {
        BookSearchPagingAdapter {
            viewModel.setClickedBookItem(it)
            fragment.onReplaceFragment(BookDetailFragment())
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
