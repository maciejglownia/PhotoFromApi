package com.glownia.maciej.photofromapi.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.glownia.maciej.photofromapi.api.PhotosApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotosRepository @Inject constructor(private val photosApi: PhotosApi) {

    fun getPhotosResults() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PhotosPagingSource(photosApi) }
        ).liveData
}