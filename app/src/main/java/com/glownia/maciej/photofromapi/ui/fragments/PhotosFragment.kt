package com.glownia.maciej.photofromapi.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.glownia.maciej.photofromapi.R
import com.glownia.maciej.photofromapi.ui.viewmodels.PhotosViewModel

class PhotosFragment : Fragment() {

    companion object {
        fun newInstance() = PhotosFragment()
    }

    private lateinit var viewModel: PhotosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_photos, container, false)
    }

}