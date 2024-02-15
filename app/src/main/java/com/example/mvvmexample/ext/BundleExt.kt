package com.example.mvvmexample.ext

import android.os.Bundle
import android.os.Parcelable

fun Bundle.putAny(key: String, value: Any) {
    // Any 타입의 값을 번들에 추가하는 함수
    // 해당 값의 타입에 따라 적절한 putXXX 함수를 호출하여 번들에 추가합니다.
    when (value) {
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Boolean -> putBoolean(key, value)
        is Parcelable -> putParcelable(key, value)
        // 다른 타입의 경우에도 필요에 따라 추가합니다.
        else -> throw IllegalArgumentException("Unsupported type: ${value.javaClass.name}")
    }
}