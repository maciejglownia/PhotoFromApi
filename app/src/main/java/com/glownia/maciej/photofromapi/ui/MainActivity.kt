package com.glownia.maciej.photofromapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.glownia.maciej.photofromapi.R
import com.glownia.maciej.photofromapi.ui.fragments.PhotosFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PhotosFragment.newInstance())
                .commitNow()
        }
    }
}