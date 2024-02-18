package com.example.mvvmexample.ui.book

import android.content.Context
import android.view.View
import com.example.mvvmexample.ui.base.BaseListAdapter
import android.widget.ImageView
import com.example.mvvmexample.BR
import com.example.mvvmexample.R
import timber.log.Timber
import javax.inject.Singleton

@Singleton
class BookSearchListAdapter(private val onClickItem: (Book) -> Unit): BaseListAdapter<Any>() {

    override fun getLayoutResourceId(): Int {
        return R.layout.item_book_search
    }

    override fun getBindingVariableId(): Int {
        return BR.bookModel
    }

    override fun onItemClick(item: Any) {
        onClickItem(item as Book)
    }

    override fun onAdapterDataChanged(itemCount: Int) {
        Timber.e("itemCount $itemCount")
    }
}