package com.example.mvvmexample.ui.book

import com.example.mvvmexample.ext.formatterMoney
import com.example.mvvmexample.ext.getContent

fun mapperToBook(book: BookEntity.GetSearchBookResponse?): List<Book> {
    return book?.documents?.map {
        Book(
            isbn = it.isbn,
            displayTitle = it.title,
            displayContents = it.contents,
            displayDatetime = it.datetime,
            displayAuthors = it.authors.getContent(),
            displaySalePrice = it.salePrice.formatterMoney(),
            thumbnail = it.thumbnail,
            status = it.status,
        )
    } ?: arrayListOf()
}