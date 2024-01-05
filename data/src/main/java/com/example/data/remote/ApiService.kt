package com.example.data.remote

import com.example.data.dto.AllCategoriesResponse
import com.example.data.dto.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    fun getProduct(@Query("id") userId:Int) : Response<ProductResponse>

    @GET("/products/categories")
    fun getCategories():Response<AllCategoriesResponse>

    @GET("/products/category/{category}")
    fun getProductsInCategory(@Path("category") category: String):Response<List<ProductResponse>>
}