package com.example.ecommerce.utils

import android.view.View
import androidx.fragment.app.Fragment
import com.example.ecommerce.R
import com.example.ecommerce.activities.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

fun Fragment.hideBottomNavigation(){
    val buttomNavigation = (activity as MainActivity).findViewById<BottomNavigationView>(R.id.bottom_nav)
    buttomNavigation.visibility = View.GONE
}

fun Fragment.showBottomNavigation(){
    val buttomNavigation = (activity as MainActivity).findViewById<BottomNavigationView>(R.id.bottom_nav)
    buttomNavigation.visibility = View.VISIBLE
}