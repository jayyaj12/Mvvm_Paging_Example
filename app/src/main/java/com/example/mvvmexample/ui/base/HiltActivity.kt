package com.example.mvvmexample.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmexample.R
import com.example.mvvmexample.ui.book.BookSearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiltActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFragmentContainer()
    }

    private fun setupFragmentContainer() {
        fragmentManager.beginTransaction().replace(R.id.fragment_container, BookSearchFragment())
            .commitAllowingStateLoss()
    }
}