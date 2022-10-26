package com.glownia.maciej.photofromapi.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    val id: String,
    val description: String?,
    val likes: Int,
    val urls: PhotoUrls,
    val user: UnsplashUser
) : Parcelable {

    @Parcelize
    data class PhotoUrls(
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String,
    ) : Parcelable

    @Parcelize
    data class UnsplashUser(
        val name: String,
        val username: String
    ) : Parcelable {
        // Line bellow is because unsplash.com requires to add analytics metadata
        val attributionUrl get() = "https://unsplash.com/$username?utm_source=PhotoFromApi&utm_medium=referral"
    }
}