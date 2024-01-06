package com.example.ecommerce.di

import android.content.Context
import com.example.data.local.SharedPreferenceRepository
import com.example.domain.repo.SharedPreferenceRepoInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideSharedPreferenceRepository(@ApplicationContext context: Context): SharedPreferenceRepoInterface {
        return SharedPreferenceRepository(context)
    }
}