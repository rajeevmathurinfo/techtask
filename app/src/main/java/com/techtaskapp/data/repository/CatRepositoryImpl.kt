package com.techtaskapp.data.repository

import com.techtaskapp.data.remote.ApiService
import com.techtaskapp.domain.model.Cat
import com.techtaskapp.domain.model.CatItem
import com.techtaskapp.domain.repository.CatRepository
import com.techtaskapp.utils.Constants.Companion.API_KEY
import com.techtaskapp.utils.Result
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(private val apiService: ApiService) : CatRepository {
    override suspend fun getCats(limit: Int): Result<List<CatItem>> {
        //return apiService.getCats(limit,"live_ck81qaOtX9Bo9iOxNPY2u5ykTiowhL40ecvgZn6JZ0bR6VH1Xqwh2rppZNWfhSRo")
        return try {
            val users = apiService.getCats(limit,API_KEY)
            Result.Success(users)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}

