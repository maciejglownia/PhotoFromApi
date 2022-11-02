package com.glownia.maciej.photofromapi.api

import com.glownia.maciej.photofromapi.data.Photo

data class PhotoResponse(
    val results: List<Photo>
)