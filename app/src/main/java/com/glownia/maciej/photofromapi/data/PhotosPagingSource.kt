package com.glownia.maciej.photofromapi.data

import androidx.paging.PagingSource
import com.glownia.maciej.photofromapi.api.PhotosApi
import retrofit2.HttpException
import java.io.IOException

private const val PHOTOS_STARTING_PAGE_INDEX = 1

class PhotosPagingSource(
    private val photosApi: PhotosApi
) : PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: PHOTOS_STARTING_PAGE_INDEX

        return try {
            val response = photosApi.getPhotos(position, params.loadSize)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == PHOTOS_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}