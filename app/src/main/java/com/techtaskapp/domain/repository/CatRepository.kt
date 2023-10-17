package com.techtaskapp.domain.repository

import com.techtaskapp.domain.model.Cat
import com.techtaskapp.domain.model.CatItem
import com.techtaskapp.utils.Result

interface CatRepository {
    suspend fun getCats(limit: Int): Result<List<CatItem>>
}
