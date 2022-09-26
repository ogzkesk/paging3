package com.example.paging3real2.network

import android.widget.BaseExpandableListAdapter
import com.example.paging3real2.network.UnsplashApi.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstance {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) : UnsplashApi = retrofit.create(UnsplashApi::class.java)
}