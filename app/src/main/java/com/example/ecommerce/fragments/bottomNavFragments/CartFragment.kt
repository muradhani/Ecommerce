package com.example.ecommerce.fragments.bottomNavFragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.entities.ProductCartEntitity
import com.example.ecommerce.viewModels.CartFragmentViewModel
import com.example.ecommerce.R
import com.example.ecommerce.adapters.CartRvAdapter
import com.example.ecommerce.adapters.ProductsCartListnter
import com.example.ecommerce.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment(), ProductsCartListnter {
    private val viewModel: CartFragmentViewModel by viewModels()
    private lateinit var binding : FragmentCartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        initAdapters()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initAdapters() {
        binding.adapter = CartRvAdapter(emptyList(),this@CartFragment)
    }

    override fun onProductClicked(product: ProductCartEntitity) {
        var action = CartFragmentDirections.actionCartFragmentToProductDetailesFragment(product.id.toString())
        findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchCartItems()
    }
}