package com.glownia.maciej.photofromapi.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.glownia.maciej.photofromapi.R
import com.glownia.maciej.photofromapi.data.Photo
import com.glownia.maciej.photofromapi.databinding.ListViewItemBinding

class PhotoAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<Photo, PhotoAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) { // PagingDataAdapter -> knows how to handle paging data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding =
            ListViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class PhotoViewHolder(private val binding: ListViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Can't to handle navigation from inside the adapter -> create an interface OnItemClickListener below
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(photo: Photo) {
            binding.apply {
                // Glide - to load images
                Glide.with(itemView)
                    .load(photo.urls.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageViewPhoto)

                textViewUsername.text = photo.user.username
                if (photo.description == null) {
                    val noDescription = "No description"
                    textViewDescription.text = noDescription
                } else {
                    textViewDescription.text = photo.description
                }
                textViewLike.text = photo.likes.toString()
                textViewUnlike.text = "0" // now only for UI. Able to set up when data is available
                // This is a way to change square color programmatically
                imageViewPaletteColor.setColorFilter(Color.parseColor(photo.color))
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(photo: Photo)
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo) =
                oldItem == newItem
        }
    }
}