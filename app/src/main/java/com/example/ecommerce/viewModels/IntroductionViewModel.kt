package com.example.ecommerce.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class IntroductionViewModel @Inject constructor(
    @ApplicationContext private val context: Context
): ViewModel() {
    private val _showOnboarding = MutableLiveData<Boolean>()
    val showOnboarding: LiveData<Boolean>
        get() = _showOnboarding

    init {
        _showOnboarding.value = !isOnboardingAlreadyShown()
    }
    fun markOnboardingAsShown() {
        val preferences = Contexts.getApplication(context).getSharedPreferences(
            "PREFS_NAME",
            Context.MODE_PRIVATE
        )
        preferences.edit().putBoolean("onboardingShown", true).apply()
    }


    private fun isOnboardingAlreadyShown(): Boolean {
        val preferences = Contexts.getApplication(context).getSharedPreferences(
            "PREFS_NAME",
            Context.MODE_PRIVATE
        )
        return preferences.getBoolean("onboardingShown", false)
    }
}