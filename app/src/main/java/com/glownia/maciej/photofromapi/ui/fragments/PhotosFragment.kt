package com.glownia.maciej.photofromapi.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.glownia.maciej.photofromapi.R
import com.glownia.maciej.photofromapi.databinding.FragmentPhotosBinding
import com.glownia.maciej.photofromapi.ui.adapters.PhotoAdapter
import com.glownia.maciej.photofromapi.ui.adapters.PhotoLoadStateAdapter
import com.glownia.maciej.photofromapi.ui.viewmodels.PhotosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : Fragment(R.layout.fragment_photos) {

    private val viewModel by viewModels<PhotosViewModel>()

    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentPhotosBinding.bind(view)

        val adapter = PhotoAdapter()
        binding.apply {
            recyclerView.setHasFixedSize(true)
            // To simulate bad internet connection in emulator go to 3 dots -> Cellular -> Set up Network type to GPRS
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = PhotoLoadStateAdapter { adapter.retry() },
                footer = PhotoLoadStateAdapter {adapter.retry() },
            )
        }

        viewModel.photos.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}