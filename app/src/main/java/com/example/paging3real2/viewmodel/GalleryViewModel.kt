package com.example.paging3real2.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging3real2.model.UnsplashPhoto
import com.example.paging3real2.repository.UnsplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val repository: UnsplashRepository) : ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)


    val photos = currentQuery.switchMap { queryString ->
        //Cache'liyoruz ->
        repository.getSearchResults(queryString).cachedIn(viewModelScope)
    }

    fun searchPhotos(query : String){
        currentQuery.value = query
    }



    companion object {
        private const val DEFAULT_QUERY = "cats"
    }

}