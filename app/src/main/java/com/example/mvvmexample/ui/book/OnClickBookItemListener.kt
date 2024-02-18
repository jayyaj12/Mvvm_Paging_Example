package com.example.mvvmexample.ui.book

interface OnClickListener<T> {
    fun onItemClicked(item: T)
}