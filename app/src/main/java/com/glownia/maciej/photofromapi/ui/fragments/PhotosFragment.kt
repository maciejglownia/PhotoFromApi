package com.glownia.maciej.photofromapi.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
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
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_photos, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                val searchView = menuItem.actionView as SearchView
                searchView.setOnQueryTextListener(object : OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if (query != null) {
                            binding.recyclerView.scrollToPosition(0)
                            viewModel.searchPhotos(query)
                            searchView.clearFocus()
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return true
                    }
                })
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        _binding = FragmentPhotosBinding.bind(view)

        val adapter = PhotoAdapter()
        binding.apply {
            recyclerView.setHasFixedSize(true)
            // To simulate bad internet connection in emulator go to 3 dots -> Cellular -> Set up Network type to GPRS
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = PhotoLoadStateAdapter { adapter.retry() },
                footer = PhotoLoadStateAdapter { adapter.retry() },
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