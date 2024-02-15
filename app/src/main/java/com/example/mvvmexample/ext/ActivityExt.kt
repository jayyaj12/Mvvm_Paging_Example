package com.example.mvvmexample.ext

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


fun AppCompatActivity.addFragment(containerViewId: Int, fragment: Fragment, addBackStack: Boolean = false) {
    this.supportFragmentManager.beginTransaction().apply {
        replace(containerViewId, fragment)
        if(addBackStack) addToBackStack(fragment.tag)
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        commit()
    }
}
