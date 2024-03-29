package com.example.mvvmexample.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.example.mvvmexample.R
import com.example.mvvmexample.ext.addFragment
import com.example.mvvmexample.ui.book.Book
import com.example.mvvmexample.ui.book.BookSearchFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.Duration

abstract class BaseActivity<T: ViewDataBinding>(private val layoutResId: Int): AppCompatActivity() {

    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.lifecycleOwner = this

        initAfterBinding()
    }

    // 액티비티 별로 사용해야 하는 코드 작성
    protected abstract fun initAfterBinding()

    fun showToast(message: String, duration: Int) {
        Toast.makeText(this, message, duration).show()
    }
}