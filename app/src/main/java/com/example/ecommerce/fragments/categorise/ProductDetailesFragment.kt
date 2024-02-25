package com.example.ecommerce.fragments.categorise

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.models.states.State
import com.example.ecommerce.R
import com.example.ecommerce.adapters.HomeViewPagerAdapter
import com.example.ecommerce.adapters.ViewPagerImagerAdapter
import com.example.ecommerce.databinding.FragmentProductDetailesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailesFragment : Fragment() {

    private val viewModel: ProductDetailesViewModel by viewModels()
    private lateinit var binding :FragmentProductDetailesBinding
    private var productId :String? = null
    private lateinit var viewPagerAdapter: ViewPagerImagerAdapter
    private val args:ProductDetailesFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailesBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        productId = args.productId
        viewModel.getProductData(productId!!)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.product.collect{
                if (it is State.Success){
                    binding.product = it.data
                    viewPagerAdapter = ViewPagerImagerAdapter(it.data.images)
                    binding.viewPager.adapter = viewPagerAdapter
                }
            }
        }
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                findNavController().popBackStack() // to clear previous navigation history
//
//            }
//        })

    }


}