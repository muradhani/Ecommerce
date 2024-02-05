package com.example.ecommerce.di

import com.example.data.remote.ApiService
import com.example.data.repo.CategoriesRepoImpl
import com.example.data.repo.UserRepoImpl
import com.example.domain.entities.ProductEntity
import com.example.domain.mapper.Mapper
import com.example.domain.mapper.ProductMapper
import com.example.domain.models.Product
import com.example.domain.repo.CategoriesRepoInterface
import com.example.domain.repo.UserRepoInterface
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideUserRepo(firebaseAuth: FirebaseAuth): UserRepoInterface {
        return UserRepoImpl(firebaseAuth)
    }
    @Provides
    fun productMapper(): Mapper<ProductEntity, Product> {
        return ProductMapper()
    }

    @Provides
    @Singleton
    fun provideCategoryRepo(apiService: ApiService): CategoriesRepoInterface {
        return CategoriesRepoImpl(apiService)
    }
    @Provides
    fun provideApiservice(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun okhttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
    }
}