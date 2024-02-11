package com.example.ecommerce

import android.view.View
import androidx.fragment.app.Fragment
import com.example.ecommerce.activities.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

fun Fragment.hideNavigation(){
    (activity as MainActivity).findViewById<BottomNavigationView>(R.id.bottom_nav).visibility = View.GONE
}
fun Fragment.showNavigation(){
    (activity as MainActivity).findViewById<BottomNavigationView>(R.id.bottom_nav).visibility = View.VISIBLE
}