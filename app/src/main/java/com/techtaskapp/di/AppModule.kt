package com.techtaskapp.di

import com.techtaskapp.data.remote.ApiService
import com.techtaskapp.data.repository.CatRepositoryImpl
import com.techtaskapp.domain.repository.CatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideCatRepository(apiService: ApiService): CatRepository {
        return CatRepositoryImpl(apiService)
    }
}

