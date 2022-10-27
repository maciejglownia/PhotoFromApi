package com.glownia.maciej.photofromapi.di

import com.glownia.maciej.photofromapi.api.PhotosApi
import com.glownia.maciej.photofromapi.api.PhotosApi.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providePhotoApi(retrofit: Retrofit): PhotosApi =
        retrofit.create(PhotosApi::class.java)
}