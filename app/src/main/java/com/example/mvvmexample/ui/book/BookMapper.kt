package com.example.mvvmexample.ui.book

fun mapperToBook(book: BookEntity.GetSearchBookResponse?): List<Book> {
    return book?.documents?.map {
        Book(
            isbn = it.isbn,
            title = it.title,
            contents = it.contents,
            datetime = it.datetime,
            authors = it.authors,
            salePrice = it.salePrice,
            thumbnail = it.thumbnail,
            status = it.status,
        )
    } ?: arrayListOf()
}