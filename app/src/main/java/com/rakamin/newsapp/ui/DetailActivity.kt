package com.rakamin.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.rakamin.newsapp.R
import com.rakamin.newsapp.databinding.ActivityDetailBinding
import com.rakamin.newsapp.util.DateFormat

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load(intent.getStringExtra(DETAIL_IMG))
            .centerCrop()
            .into(binding.ivArticleNews)

        val formattedDate = DateFormat.formatDate(intent.getStringExtra(DETAIL_DATE)!!)

        binding.tvTitleNews.text = intent.getStringExtra(DETAIL_TITLE)
        binding.tvAuthor.text = intent.getStringExtra(DETAIL_AUTHOR)
        binding.tvPublished.text = "Published : $formattedDate"
        binding.tvSource.text = intent.getStringExtra(DETAIL_SOURCE)
        binding.tvDescNews.text = intent.getStringExtra(DETAIL_DESC)
    }

    companion object {
        const val DETAIL_TITLE = "detail_title"
        const val DETAIL_IMG = "detail_img"
        const val DETAIL_DESC = "detail_desc"
        const val DETAIL_SOURCE = "detail_source"
        const val DETAIL_AUTHOR = "detail_author"
        const val DETAIL_DATE = "detail_date"
    }
}