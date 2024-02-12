package com.example.mvvmexample.ui.book

data class Book(
    val isbn: String,
    val title: String,
    val contents: String,
    val datetime: String,
    val authors: List<String>,
    val salePrice: Int,
    val thumbnail: String,
    val status: String
)
