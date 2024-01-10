package com.example.ecommerce.di

import com.example.data.dto.CategoryProductsItem
import com.example.data.dto.ProductResponse
import com.example.data.mapper.ProductMapper
import com.example.data.remote.ApiService
import com.example.data.repo.CategoriesRepoImpl
import com.example.data.repo.UserRepoImpl
import com.example.domain.entities.Product
import com.example.domain.mapper.Mapper
import com.example.domain.repo.CategoriesRepoInterface
import com.example.domain.repo.UserRepoInterface
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.FragmentScoped
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
    fun productMapper(): Mapper<CategoryProductsItem, Product> {
        return ProductMapper()
    }

    @Provides
    @Singleton
    fun provideCategoryRepo(apiService: ApiService,mapper:Mapper<CategoryProductsItem, Product>): CategoriesRepoInterface {
        return CategoriesRepoImpl(apiService,mapper as ProductMapper)
    }
    @Provides
    fun provideApiservice(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
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