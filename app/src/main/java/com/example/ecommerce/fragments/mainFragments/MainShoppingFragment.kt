package com.example.ecommerce.fragments.mainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ecommerce.databinding.FragmentMainShoppingBinding


class MainShoppingFragment : Fragment() {
    private val viewModel: MainShoppingViewModel by viewModels()
    private lateinit var binding:FragmentMainShoppingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainShoppingBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val navHostFragment =
            childFragmentManager.findFragmentById(com.example.ecommerce.R.id.nav_host_fragment2) as NavHostFragment?
        val navController = navHostFragment!!.navController

        binding.bottomNav.setupWithNavController(navController)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}