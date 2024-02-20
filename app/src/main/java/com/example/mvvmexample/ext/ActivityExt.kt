package com.example.mvvmexample.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.mvvmexample.ui.base.BaseActivity


fun AppCompatActivity.addFragment(containerViewId: Int, fragment: Fragment, addBackStack: Boolean = false) {
    this.supportFragmentManager.beginTransaction().apply {
        replace(containerViewId, fragment)
        if(addBackStack) addToBackStack(fragment.tag)
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        commit()
    }
}

fun AppCompatActivity.moveActivity(nowActivity: Activity, moveActivity: Activity, isFinish: Boolean = false) {
    startActivity(Intent(nowActivity, moveActivity::class.java))
    if(isFinish) finish()
}
