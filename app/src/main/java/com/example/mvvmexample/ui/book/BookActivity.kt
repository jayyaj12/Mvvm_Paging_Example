package com.example.mvvmexample.ui.book

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mvvmexample.R
import com.example.mvvmexample.databinding.ActivityBookBinding
import com.example.mvvmexample.ext.addFragment
import com.example.mvvmexample.ext.moveActivity
import com.example.mvvmexample.ui.TestActivity
import com.example.mvvmexample.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookActivity: BaseActivity<ActivityBookBinding>(R.layout.activity_book) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initAfterBinding() {
        binding.bookActivity = this
        this.addFragment(R.id.fragment_container, BookSearchFragment())
    }

    // 다음 액티비티로 이동하기 버튼 클릭
    fun onClickedNextActivityBtn() {
        this.moveActivity(this, TestActivity(), true)
    }
}