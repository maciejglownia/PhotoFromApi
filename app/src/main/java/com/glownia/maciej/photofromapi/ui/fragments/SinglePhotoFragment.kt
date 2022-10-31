package com.glownia.maciej.photofromapi.ui.fragments

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.glownia.maciej.photofromapi.R
import com.glownia.maciej.photofromapi.databinding.FragmentSinglePhotoBinding

class SinglePhotoFragment : Fragment(R.layout.fragment_single_photo) {

    private val args by navArgs<SinglePhotoFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSinglePhotoBinding.bind(view)

        binding.apply {
            val photo = args.photo

            Glide.with(this@SinglePhotoFragment)
                .load(photo.urls.regular) // use "full" image if have unlimited internet plan - huge size
                .error(R.drawable.ic_error)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean,
                    ): Boolean {
                        progressBar.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean,
                    ): Boolean {
                        progressBar.isVisible = false
                        textViewUsername.isVisible = true
                        textViewDescription.isVisible = photo.description != null
                        return false
                    }
                })
                .into(imageView)
            textViewDescription.text = photo.description

            // Functionality to send user to the profile of the photo's author by clicking on the author text
            val uri = Uri.parse(photo.user.attributionUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)

            textViewUsername.apply {
                text = "Photo by ${photo.user.name}"
                setOnClickListener {
                    context.startActivity(intent)
                }
                paint.isUnderlineText = true
            }

        }
    }
}