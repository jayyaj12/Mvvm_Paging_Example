package com.example.mvvmexample.ui.book

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    val isbn: String,
    val displayTitle: String,
    val displayContents: String,
    val displayDatetime: String,
    val displayAuthors: String,
    val displaySalePrice: String,
    val thumbnail: String,
    val status: String
): Parcelable
