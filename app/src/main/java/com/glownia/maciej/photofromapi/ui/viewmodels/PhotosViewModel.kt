package com.glownia.maciej.photofromapi.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.glownia.maciej.photofromapi.data.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val repository: PhotosRepository
) : ViewModel() {

    val photos = repository.getPhotosResults()

    fun getPhotos() {
        repository.getPhotosResults().cachedIn(viewModelScope)
    }
}