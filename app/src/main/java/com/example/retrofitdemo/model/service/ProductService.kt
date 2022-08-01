package com.example.retrofitdemo.model.service

import com.example.retrofitdemo.model.ProductInfo
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {

    @GET("/v3/3588163c-a9c8-488c-af9a-534b392e7128")
    suspend fun getProduct(): Response<ProductInfo>

}