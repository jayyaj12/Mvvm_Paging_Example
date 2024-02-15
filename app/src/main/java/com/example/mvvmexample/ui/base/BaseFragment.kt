package com.example.mvvmexample.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.mvvmexample.ext.onReplaceFragment
import com.example.mvvmexample.ext.popFragment

abstract class BaseFragment<T: ViewDataBinding>(@LayoutRes private val layoutResId: Int): Fragment() {
    private var _binding: T? = null
    val binding get() = _binding!!
    private lateinit var backPressedCallback: OnBackPressedCallback


    override fun onAttach(context: Context) {
        super.onAttach(context)

        backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                this@BaseFragment.popFragment()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, backPressedCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAfter()
    }

    protected abstract fun initAfter()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}