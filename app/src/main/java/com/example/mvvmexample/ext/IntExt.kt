package com.example.mvvmexample.ext

import java.text.DecimalFormat


// 천자리 콤마 + 원
fun Int.formatterMoney(): String {
    return "${DecimalFormat("#,###").format(this)}원"
}