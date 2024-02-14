package com.example.mvvmexample.ui.book

import android.os.Bundle
import com.example.mvvmexample.R
import com.example.mvvmexample.databinding.ActivityBookBinding
import com.example.mvvmexample.ui.base.BaseActivity

class BookActivity: BaseActivity<ActivityBookBinding>(R.layout.activity_book) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.fragmentContainer
    }

    override fun initAfterBinding() {
        TODO("Not yet implemented")
        showToast("asd", 1000)
    }

}