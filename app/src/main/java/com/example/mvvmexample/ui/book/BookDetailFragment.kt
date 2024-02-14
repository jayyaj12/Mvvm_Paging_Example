package com.example.mvvmexample.ui.book

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.example.mvvmexample.databinding.FragmentBookBinding
import com.example.mvvmexample.databinding.FragmentBookDetailBinding
import com.example.mvvmexample.ext.onReplaceFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailFragment(val item: Book) : Fragment() {

    private var _binding: FragmentBookDetailBinding? = null
    private val binding: FragmentBookDetailBinding get() = _binding!!
    private lateinit var backPressedCallback: OnBackPressedCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)

        backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                this@BookDetailFragment.onReplaceFragment(BookSearchFragment())
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, backPressedCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookDetailBinding.inflate(layoutInflater)
        binding.bookDetailFragment = this@BookDetailFragment
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}