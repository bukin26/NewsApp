package com.gmail.newstestingapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gmail.newstestingapp.R
import com.gmail.newstestingapp.databinding.ItemNewsBinding
import com.gmail.newstestingapp.model.NewsResponseItem

class NewsAdapter : PagingDataAdapter<NewsResponseItem, NewsAdapter.Holder>(GifsDiffCallback()) {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val news = getItem(position) ?: return
        with(holder.binding) {
            if (news.title.isNotBlank()) titleTextView.text = news.title
            if (news.time.isNotBlank()) timeTextView.text = news.time
            news.img?.let { loadGif(newsImageView, it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    private fun loadGif(imageView: ImageView, url: String) {
        val context = imageView.context
        if (url.isNotBlank()) {
            Glide.with(context)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_image_24)
                .into(imageView)
        } else {
            Glide.with(context)
                .load(R.drawable.ic_baseline_image_24)
                .into(imageView)
        }
    }


    class Holder(
        val binding: ItemNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
    }
}

class GifsDiffCallback : DiffUtil.ItemCallback<NewsResponseItem>() {
    override fun areItemsTheSame(oldItem: NewsResponseItem, newItem: NewsResponseItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: NewsResponseItem, newItem: NewsResponseItem): Boolean {
        return oldItem == newItem
    }
}