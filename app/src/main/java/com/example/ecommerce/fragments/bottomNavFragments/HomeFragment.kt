package com.example.ecommerce.fragments.bottomNavFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ecommerce.viewModels.HomeFragmentViewModel
import com.example.ecommerce.adapters.HomeViewPagerAdapter
import com.example.ecommerce.databinding.FragmentHomeBinding
import com.example.ecommerce.fragments.categorise.MainCategoryFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var binding : FragmentHomeBinding
    private lateinit var categotiesFramgments : List<Fragment>
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
        observeCategoryList()
    }
    private fun observeCategoryList(){
        viewModel.categoriesList.observe(viewLifecycleOwner) { categoriesList ->
            if (categoriesList != null) {
                lifecycleScope.launch {
                    val fragments = createFragments(categoriesList)
                    withContext(Dispatchers.Main){
                        val viewPagerAdapter = createViewPagerAdapter(fragments)
                        setupViewPagerAndTabs(categoriesList, viewPagerAdapter)
                    }
                }
            }
        }
    }
    private fun setupViewPagerAndTabs(categoriesList: List<String>, viewPagerAdapter: HomeViewPagerAdapter) {
        binding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            if (position < categoriesList.size) {
                tab.text = categoriesList[position] ?: "Tab $position"
            } else {
                tab.text = "Tab $position"
            }
        }.attach()
    }
    private fun createViewPagerAdapter(fragments: List<Fragment>): HomeViewPagerAdapter {
        return HomeViewPagerAdapter(fragments, requireParentFragment().childFragmentManager, lifecycle)
    }

    private suspend fun createFragments(categories:List<String>):List<Fragment> {
      return listOf(MainCategoryFragment().apply {
          arguments = Bundle().apply {
              putString("category",categories[1])
          }
      })
    }
}