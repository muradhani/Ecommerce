package com.example.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.domain.repo.SharedPreferenceRepoInterface
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferenceRepository @Inject constructor(@ApplicationContext private val context: Context):SharedPreferenceRepoInterface {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    override fun saveLoginState(isLoggedIn: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", isLoggedIn)
        editor.apply()
    }

    override fun getLoginState(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }
}