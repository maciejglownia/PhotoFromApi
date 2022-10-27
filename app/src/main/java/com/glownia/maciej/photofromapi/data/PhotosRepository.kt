package com.glownia.maciej.photofromapi.data

import com.glownia.maciej.photofromapi.api.PhotosApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotosRepository @Inject constructor(private val photosApi: PhotosApi) {

}