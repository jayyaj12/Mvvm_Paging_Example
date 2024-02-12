package com.example.mvvmexample.ui.book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmexample.databinding.ItemBookSearchBinding
import timber.log.Timber
import javax.inject.Singleton

@Singleton
class BookSearchPagingAdapter(private val onClickItem: (Book) -> Unit) : PagingDataAdapter<Book, BookSearchPagingAdapter.BookViewHolder>(
    bookDiffUtil
) {

    companion object {
        private val bookDiffUtil = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.isbn == newItem.isbn
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class BookViewHolder(private val binding: ItemBookSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Book) {
            binding.bookModel = item

            binding.itemCl.setOnClickListener {
                onClickItem(item)
            }
        }
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val bookItem = getItem(position)
        bookItem?.let { item ->
            holder.onBind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            ItemBookSearchBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

}