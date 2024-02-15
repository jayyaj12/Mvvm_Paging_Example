package com.example.mvvmexample.ui.book

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mvvmexample.R
import com.example.mvvmexample.databinding.ActivityBookBinding
import com.example.mvvmexample.ext.addFragment
import com.example.mvvmexample.ui.base.BaseActivity

class BookActivity: BaseActivity<ActivityBookBinding>(R.layout.activity_book) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initAfterBinding() {
        addFragment(BookSearchFragment())
    }

    fun addFragment(fragment: Fragment, addBackStack: Boolean = false) {
        this.addFragment(
            containerViewId = R.id.fragment_container,
            fragment = fragment,
            addBackStack = addBackStack
        )
    }

}