package com.rakamin.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.rakamin.newsapp.R
import com.rakamin.newsapp.adapter.ArticleAdapter
import com.rakamin.newsapp.adapter.HeadlineAdapter
import com.rakamin.newsapp.databinding.ActivityMainBinding
import com.rakamin.newsapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var headlineAdapter: HeadlineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        articleAdapter = ArticleAdapter()
        headlineAdapter = HeadlineAdapter()

        val layoutHorizontalManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        val layoutVerticalManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        val itemDecoration = DividerItemDecoration(this, layoutVerticalManager.orientation)

        binding.apply {
            rvHeadlineNews.layoutManager = layoutHorizontalManager
            rvHeadlineNews.setHasFixedSize(true)
            rvHeadlineNews.adapter = headlineAdapter

            rvNews.layoutManager = layoutVerticalManager
            rvNews.addItemDecoration(itemDecoration)
            rvNews.setHasFixedSize(true)
            rvNews.adapter = articleAdapter
        }

        mainViewModel.listArticle.observe(this, Observer{
            articleAdapter.submitData(lifecycle, it)
        })

        mainViewModel.listHeadline.observe(this, Observer{
            headlineAdapter.submitData(lifecycle, it)
        })



    }
}