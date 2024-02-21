package com.example.ecommerce.fragments.categorise


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.domain.models.Product
import com.example.ecommerce.R
import com.example.ecommerce.adapters.BestDealsProductListnter
import com.example.ecommerce.adapters.BestDealsRvAdapter
import com.example.ecommerce.adapters.ProductsListnter
import com.example.ecommerce.adapters.ProductsRvAdapter
import com.example.ecommerce.adapters.SpecialProductsListnter
import com.example.ecommerce.adapters.SpecialRvAdapter
import com.example.ecommerce.databinding.FragmentMainCategoryBinding
import com.example.ecommerce.utils.NavigationListener
import com.example.ecommerce.viewModels.MainCategoryViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainCategoryFragment : Fragment(), ProductsListnter, BestDealsProductListnter,
    SpecialProductsListnter {
    private val viewModel: MainCategoryViewModel by viewModels()
    private lateinit var binding: FragmentMainCategoryBinding
    private lateinit var category : String
    lateinit var listener: NavigationListener
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

        listener.onProductSelected(productId = product.id.toString())
    }

}