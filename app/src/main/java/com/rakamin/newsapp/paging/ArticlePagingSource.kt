package com.rakamin.newsapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rakamin.newsapp.repository.data.remote.response.ArticlesItem
import com.rakamin.newsapp.repository.data.remote.retrofit.ApiService

class ArticlePagingSource(private val apiService: ApiService) : PagingSource<Int, ArticlesItem>(){
    override fun getRefreshKey(state: PagingState<Int, ArticlesItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesItem> {
        return try {
            val position = params.key ?: 1
            val response = apiService.getEverythingNews(q = "crypto", sortBy = "popularity",position)

            return LoadResult.Page(
                data = response.articles,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.totalResults) null else position + 1
            )
        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }
}