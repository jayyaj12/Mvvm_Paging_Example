package com.example.mvvmexample.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

// 이미지 설정
@BindingAdapter("setImageUrl")
fun ImageView.bindImageUrl(url: String) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}