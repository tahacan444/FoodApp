package com.tahacan.yemekprojesi.di

import com.tahacan.yemekprojesi.data.datasource.YemeklerDataSource
import com.tahacan.yemekprojesi.data.repo.YemeklerRepository
import com.tahacan.yemekprojesi.retrofit.ApiUtlis
import com.tahacan.yemekprojesi.retrofit.YemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideYemeklerRepository(yemeklerDataSource: YemeklerDataSource) : YemeklerRepository {
        return YemeklerRepository(yemeklerDataSource)
    }

    @Provides
    @Singleton
    fun provideYemeklerDataSource(yemeklerDao: YemeklerDao) : YemeklerDataSource {
        return YemeklerDataSource(yemeklerDao)
    }

    @Provides
    @Singleton
    fun provideYemeklerDao() : YemeklerDao {
        return ApiUtlis.getYemeklerDao()
    }
}