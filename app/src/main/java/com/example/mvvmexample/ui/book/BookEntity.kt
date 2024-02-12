package com.example.mvvmexample.ui.book

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class BookEntity {
    @Serializable
    data class GetSearchBookResponse(
        val meta: GetSearchBookMetaResponse,
        val documents: List<GetSearchBookDocumentsResponse>,
    )

    @Serializable
    data class GetSearchBookMetaResponse(
        @SerialName("total_count") val totalCount: Int,
        @SerialName("pageable_count") val pageableCount: Int,
        @SerialName("is_end") val isEnd: Boolean,
    )

    @Serializable
    data class GetSearchBookDocumentsResponse(
        val title: String,
        val contents: String,
        val url: String,
        val isbn: String,
        val datetime: String,
        val authors: List<String>,
        val publisher: String,
        val translators: List<String>,
        val price: Int,
        @SerialName("sale_price") val salePrice: Int,
        val thumbnail: String,
        val status: String,
    )
}
