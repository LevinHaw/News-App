package com.rakamin.newsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.rakamin.newsapp.R
import com.rakamin.newsapp.adapter.ArticleAdapter
import com.rakamin.newsapp.adapter.HeadlineAdapter
import com.rakamin.newsapp.databinding.ActivityMainBinding
import com.rakamin.newsapp.repository.data.remote.response.ArticlesItem
import com.rakamin.newsapp.util.DateFormat
import com.rakamin.newsapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
        articleAdapter = ArticleAdapter() {
                articleNews ->newsOnClick(articleNews)
        }
        headlineAdapter = HeadlineAdapter(){
                articleNews ->newsOnClick(articleNews)
        }

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

        lifecycleScope.launch {
            articleAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading
            }
        }
    }

    private fun newsOnClick(news : ArticlesItem){
        val moveWithDataIntent = Intent(this@MainActivity, DetailActivity::class.java)

        moveWithDataIntent.putExtra(DetailActivity.DETAIL_TITLE, news.title)
        moveWithDataIntent.putExtra(DetailActivity.DETAIL_IMG,news.urlToImage)
        moveWithDataIntent.putExtra(DetailActivity.DETAIL_DESC,news.description)
        moveWithDataIntent.putExtra(DetailActivity.DETAIL_SOURCE,news.source?.name)
        moveWithDataIntent.putExtra(DetailActivity.DETAIL_AUTHOR,news.author)
        moveWithDataIntent.putExtra(DetailActivity.DETAIL_DATE,news.publishedAt)

        startActivity(moveWithDataIntent)
    }
}