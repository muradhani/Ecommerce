package com.example.ecommerce.fragments.categorise

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentMainCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainCategoryFragment : Fragment() {

    private val viewModel: MainCategoryViewModel by viewModels()
    private lateinit var binding : FragmentMainCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainCategoryBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

}