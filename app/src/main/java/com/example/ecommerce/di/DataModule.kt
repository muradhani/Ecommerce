package com.example.ecommerce.di

import com.example.data.repo.UserRepoImpl
import com.example.domain.models.User
import com.example.domain.repo.UserRepoInterface
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideUserRepo(firebaseAuth: FirebaseAuth): UserRepoInterface {
        return UserRepoImpl(firebaseAuth)
    }
}