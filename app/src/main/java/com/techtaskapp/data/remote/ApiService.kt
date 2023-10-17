package com.techtaskapp.data.remote

import com.techtaskapp.domain.model.Cat
import com.techtaskapp.domain.model.CatItem
import com.techtaskapp.utils.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/images/search/")
    suspend fun getCats(@Query("limit") limit: Int, @Query("api_key") apiKey: String): List<CatItem> // Assuming you're getting a User model
}
