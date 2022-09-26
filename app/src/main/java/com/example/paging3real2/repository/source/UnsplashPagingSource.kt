package com.example.paging3real2.repository.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3real2.model.UnsplashPhoto
import com.example.paging3real2.network.UnsplashApi
import retrofit2.HttpException
import java.io.IOException

class UnsplashPagingSource(private val api : UnsplashApi, private val query : String) : PagingSource<Int,UnsplashPhoto>(){
    override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val position = params.key ?: 1

        return try{
            val response = api.searchPhotos(query,1,params.loadSize)
            val photoList = response.results
            LoadResult.Page(photoList,
            if (position == 1) null else position -1,
            if (photoList.isEmpty()) null else position +1)
        }catch (e : IOException){
            println("IO exception")
            LoadResult.Error(e)
        }catch (e : HttpException){
            println("Http exception")
            LoadResult.Error(e)
        }
    }
}