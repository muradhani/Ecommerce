package com.example.ecommerce.fragments.categorise

import android.R
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.models.Product
import com.example.ecommerce.adapters.BestDealsProductListnter
import com.example.ecommerce.adapters.BestDealsRvAdapter
import com.example.ecommerce.adapters.ProductsListnter
import com.example.ecommerce.adapters.ProductsRvAdapter
import com.example.ecommerce.adapters.SpecialProductsListnter
import com.example.ecommerce.adapters.SpecialRvAdapter
import com.example.ecommerce.databinding.FragmentMainCategoryBinding
import com.example.ecommerce.fragments.bottomNavFragments.HomeFragmentDirections
import com.example.ecommerce.viewModels.MainCategoryViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainCategoryFragment : Fragment(), ProductsListnter, BestDealsProductListnter,
    SpecialProductsListnter {
    private val viewModel: MainCategoryViewModel by viewModels()
    private lateinit var binding: FragmentMainCategoryBinding
    private lateinit var category : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainCategoryBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        category = arguments?.getString("category").toString()
        viewModel.getProducts(category)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapters()
    }



    private fun initAdapters() {
        binding.productsAdapter = ProductsRvAdapter(emptyList(), this)
        binding.bestDealsAdapter = BestDealsRvAdapter(emptyList(), this)
        binding.bestProductsAdapter = SpecialRvAdapter(emptyList(), this)
    }

    override fun onProductClicked(product: Product) {
        var id = product.id.toString()
        val action =  HomeFragmentDirections.actionHomeFragmentToProductDetailesFragment(id)
        findNavController().navigate(action)
    }

}