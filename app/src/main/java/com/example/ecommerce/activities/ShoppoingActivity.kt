package com.example.ecommerce.activities

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ecommerce.R
import com.example.ecommerce.databinding.ActivityShoppoingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppoingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppoingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShoppoingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNav.setupWithNavController(findNavController(R.id.nav_host_fragment))
        Log.i("main","hello")
    }

}
