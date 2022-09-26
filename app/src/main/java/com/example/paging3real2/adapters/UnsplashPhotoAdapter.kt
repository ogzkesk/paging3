package com.example.paging3real2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.paging3real2.R
import com.example.paging3real2.databinding.ItemUnsplashPhotoBinding
import com.example.paging3real2.model.UnsplashPhoto

class UnsplashPhotoAdapter(private val listener : OnItemClickListener) : PagingDataAdapter<UnsplashPhoto,UnsplashPhotoAdapter.UnsplashViewHolder>(PHOTO_COMPARATOR) {


    override fun onBindViewHolder(holder: UnsplashViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null){
            holder.bind(currentItem)
        }
    }

    inner class UnsplashViewHolder(val binding : ItemUnsplashPhotoBinding) : RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if ( position != RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if(item != null){
                        listener.onItemClick(item)
                    }
                }

            }
        }

        fun bind(photo : UnsplashPhoto){
            binding.apply {
                Glide.with(itemView)
                    .load(photo.urls.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_baseline_error_24)
                    .into(ivPhotos)

                tvTitle.text = photo.user.username
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(photo : UnsplashPhoto)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnsplashViewHolder {
        return UnsplashViewHolder(ItemUnsplashPhotoBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<UnsplashPhoto>() {
            override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto) =
                oldItem == newItem
        }
    }

}