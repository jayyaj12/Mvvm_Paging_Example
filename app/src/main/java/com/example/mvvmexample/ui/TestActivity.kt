package com.example.mvvmexample.ui

import android.content.Intent
import com.example.mvvmexample.R
import com.example.mvvmexample.databinding.ActivityTestBinding
import com.example.mvvmexample.ui.base.BaseActivity
import com.example.mvvmexample.ui.book.BookActivity

class TestActivity: BaseActivity<ActivityTestBinding>(R.layout.activity_test) {
    override fun initAfterBinding() {
    }
}