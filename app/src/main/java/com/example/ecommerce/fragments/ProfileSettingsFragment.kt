package com.example.ecommerce.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentProfileSettingsBinding
import com.example.ecommerce.viewModels.ProfileSettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileSettingsFragment : Fragment() {

    private val viewModel: ProfileSettingsViewModel by viewModels()
    private lateinit var binding :FragmentProfileSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile_settings,container,false)
        binding.lifecycleOwner = this
        return binding.root
    }



}