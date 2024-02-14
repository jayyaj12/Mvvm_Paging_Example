package com.example.mvvmexample.ext

import timber.log.Timber

fun List<Any>.getContent(): String {
    var data = ""
    return if(this.isNotEmpty()) {
        this.forEach {
            data += "$it,"
        }
        data.substring(0, data.length - 1)
    } else {
        ""
    }
}