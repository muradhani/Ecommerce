package com.example.ecommerce.fragments.bottomNavFragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.ecommerce.viewModels.HomeFragmentViewModel
import com.example.ecommerce.R
import com.example.ecommerce.adapters.HomeViewPagerAdapter
import com.example.ecommerce.databinding.FragmentHomeBinding
import com.example.ecommerce.fragments.categorise.AccessoryFragment
import com.example.ecommerce.fragments.categorise.ChairFragment
import com.example.ecommerce.fragments.categorise.CupBoardFragment
import com.example.ecommerce.fragments.categorise.FurnitureFragment
import com.example.ecommerce.fragments.categorise.MainCategoryFragment
import com.example.ecommerce.fragments.categorise.TableFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categotiesFramgments = arrayListOf<Fragment>(
            MainCategoryFragment(),
            ChairFragment(),
            CupBoardFragment(),
            TableFragment(),
            AccessoryFragment(),
            FurnitureFragment()
        )
        val viewPagerAdapter = HomeViewPagerAdapter(categotiesFramgments,childFragmentManager,lifecycle)
        binding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab , position ->
            when(position){
                0 -> tab.text = "Main"
                1 -> tab.text = "Chairs"
                2 -> tab.text = "CupBoards"
                3 -> tab.text = "Tables"
                4 -> tab.text = "Accessories"
                5 -> tab.text = "Furniture"
            }
        }.attach()
    }
}