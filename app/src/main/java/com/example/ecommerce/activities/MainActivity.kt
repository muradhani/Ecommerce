package com.example.ecommerce.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ecommerce.R
import com.example.ecommerce.bindingAdapters.observeLogin
import com.example.ecommerce.databinding.ActivityMainBinding
import com.example.ecommerce.viewModels.LoginViewModel
import com.example.ecommerce.viewModels.MainActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel :MainActivityViewModel by viewModels()
    private val loginViewModel : LoginViewModel by viewModels()

    lateinit var binding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        lifecycleScope.launch {
            viewModel.getLoginState()
        }
        observeLogin()
    }
    private fun observeLogin(){
        viewModel.login.observe(this, Observer {
            if (it){
                val navController = binding.fragmentContainerLoginRegister.findNavController()
                binding.bottomNav.setupWithNavController(navController)
            }
        })
    }
}