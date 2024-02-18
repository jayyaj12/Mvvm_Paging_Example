package com.example.mvvmexample.ext

import com.example.mvvmexample.ui.base.BaseListAdapter
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmexample.ui.base.BaseFragment
import com.example.mvvmexample.ui.base.BaseViewModel
import com.example.mvvmexample.ui.book.Book
import com.example.mvvmexample.ui.book.BookDetailFragment
import com.example.mvvmexample.ui.book.BookSearchListAdapter
import com.example.mvvmexample.ui.book.BookSearchViewModel
import timber.log.Timber


//@BindingAdapter("setFragment", "moveFragment", "requestKey", "setItem")
//fun RecyclerView.bindSetPagingItems(
//    fragment: Fragment,
//    moveFragment: Fragment,
//    requestKey: String,
//    items: PagingData<Book>
//) {
//    val adapter = if (this.adapter == null) {
//        val adapter = object : com.example.mvvmexample.ui.base.BaseListAdapter.Adapter<Book, ItemBookSearchBinding>(
//            layoutResId = R.layout.item_book_search,
//            bindingVariableId = R.id.item_cl
//        )
//        BookSearchPagingAdapter {
//            fragment.onReplaceFragment(moveFragment, requestKey, it, true)
//        }
//    } else {
//        this.adapter as BookSearchPagingAdapter
//    }
//
//    if (this.adapter == null) {
//        setHasFixedSize(true)
//
//        this.adapter = adapter
//        this.addItemDecoration(
//            DividerItemDecoration(
//                this.context, DividerItemDecoration.VERTICAL
//            )
//        )
//    }
//
//    CoroutineScope(Dispatchers.IO).launch {
//        items.let {
//            adapter.submitData(it)
//        }
//    }
//}

@BindingAdapter("bind:listAdapter", "bind:item", "bind:viewModelType", "bind:viewModel", requireAll = false)
fun RecyclerView.bindSetListAdapter(
    listAdapter: BaseListAdapter<*>,
    items: List<Any>,
    type: String,
    viewModel: BaseViewModel
) {
    this.setHasFixedSize(true)

    if (this.adapter == null) {
        this.adapter = listAdapter
    }

    when (type) {
        "bookViewModel" -> {
            val baseAdapter = this.adapter as BookSearchListAdapter
            baseAdapter.submitList(items.toMutableList())
        }

        "weatherViewModel" -> {
            val baseAdapter = BookSearchListAdapter()
            baseAdapter.submitList(items.toMutableList())
        }
    }

    this.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
        if (!canScrollVertically(1)) {
            when (type) {
                "bookViewModel" -> {
                    viewModel as BookSearchViewModel
                    viewModel.getSearchBook("scroll")
                }
            }
        }
    }
}
//
//@BindingAdapter("bind:endlessScroll", "bind:viewModelType")
//fun RecyclerView.setEndlessScroll(
//    vm: BaseViewModel, type: String
//) {
//
//    this.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
//        if (!canScrollVertically(1)) {
//            when (type) {
//                "bookViewModel" -> {
//                    vm as BookSearchViewModel
//                    vm.getSearchBook("scroll")
//                }
//            }
//        }
//    }
//}