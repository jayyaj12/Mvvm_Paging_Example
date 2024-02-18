package com.example.mvvmexample.ui.book

import com.example.mvvmexample.ui.base.BaseListAdapter
import android.widget.ImageView
import com.example.mvvmexample.BR
import com.example.mvvmexample.R
import timber.log.Timber
import javax.inject.Singleton

@Singleton
class BookSearchListAdapter(): BaseListAdapter<Any>() {

    override fun getLayoutResourceId(): Int {
        return R.layout.item_book_search
    }

    override fun getBindingVariableId(): Int {
        return BR.bookModel
    }

    override fun onItemClick(item: Any) {
    }

    override fun onAdapterDataChanged(itemCount: Int) {
        binding?.let {
            it.root.findViewById<ImageView>(R.id.thumbnail_iv)
        }
    }

}