package com.example.paging3real2.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.paging3real2.network.UnsplashApi
import com.example.paging3real2.repository.source.UnsplashPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepository @Inject constructor(private val api: UnsplashApi) {

    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(pageSize = 10, maxSize = 30, enablePlaceholders = false),
            pagingSourceFactory = { UnsplashPagingSource(api, query) }).liveData

}