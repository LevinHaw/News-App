package com.rakamin.newsapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.rakamin.newsapp.paging.ArticlePagingSource
import com.rakamin.newsapp.paging.HeadlinePagingSource
import com.rakamin.newsapp.repository.data.remote.retrofit.ApiService
import javax.inject.Inject

class ArticleRepository @Inject constructor(private val apiService : ApiService) {

    fun getArticle() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { ArticlePagingSource(apiService) }
    ).liveData

    fun getHeadline() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { HeadlinePagingSource(apiService) }
    ).liveData
}