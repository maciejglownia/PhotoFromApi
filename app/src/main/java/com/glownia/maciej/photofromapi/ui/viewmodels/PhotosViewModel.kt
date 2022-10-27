package com.glownia.maciej.photofromapi.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.glownia.maciej.photofromapi.data.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val repository: PhotosRepository
) : ViewModel() {

}