package com.rakamin.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rakamin.newsapp.R
import com.rakamin.newsapp.databinding.ItemHeadlineNewsBinding
import com.rakamin.newsapp.repository.data.remote.response.ArticlesItem

class HeadlineAdapter : PagingDataAdapter <ArticlesItem, HeadlineAdapter.HeadlineViewHolder>(DIFF_CALLBACK) {

    class HeadlineViewHolder(private val binding : ItemHeadlineNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(headline : ArticlesItem) {
            binding.tvTitleHeadline.text = headline.title
            binding.tvDate.text = headline.publishedAt
            if (headline.source != null){
                binding.tvSource.text = headline.source?.name
            } else {
                binding.tvSource.text = "Source"
            }
            Glide.with(binding.root)
                .load(headline.urlToImage)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.ivHeadlineNews)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlineViewHolder {
        val binding = ItemHeadlineNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeadlineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeadlineViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}