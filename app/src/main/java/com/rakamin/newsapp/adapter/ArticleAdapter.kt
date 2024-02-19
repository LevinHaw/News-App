package com.rakamin.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rakamin.newsapp.R
import com.rakamin.newsapp.databinding.ItemNewsBinding
import com.rakamin.newsapp.repository.data.remote.response.ArticlesItem
import com.rakamin.newsapp.util.DateFormat

class ArticleAdapter(private val onClickListener: (ArticlesItem) -> Unit) : PagingDataAdapter<ArticlesItem, ArticleAdapter.ArticleViewHolder>(DIFF_CALLBACK){

    inner class ArticleViewHolder(private val binding : ItemNewsBinding, private val onClickListener: (ArticlesItem) -> Unit): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val article = getItem(position)
                    if (article != null) {
                        onClickListener(article)
                    }
                }
            }
        }
        fun bind(article : ArticlesItem){
            binding.tvTitle.text = article.title
            if (article.author == null){
                binding.tvCredit.text = "Author"
            } else {
                binding.tvCredit.text = article.author
            }
            binding.tvDate.text = DateFormat.formatDate(article.publishedAt!!)
            Glide.with(binding.root)
                .load(article.urlToImage)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.ivNews)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}