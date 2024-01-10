package com.example.data.remote

import com.example.data.dto.AllCategoriesResponse
import com.example.data.dto.CategoryProducts
import com.example.data.dto.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getProduct(@Query("id") userId:Int) : Response<ProductResponse>

    @GET("products/categories")
    suspend fun getCategories():Response<List<String>>

    @GET("products/category/{category}")
    suspend fun getProductsInCategory(@Path("category") category: String):Response<CategoryProducts>
}