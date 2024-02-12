package com.rakamin.newsapp.repository.data.remote.retrofit

import com.rakamin.newsapp.repository.data.remote.response.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines?country=us&category=technology")
    suspend fun getHeadlineNews(
        @Query("page") page : Int
    ) : ArticleResponse

    @GET("v2/everything?q=crypto&sortBy=popularity")
    suspend fun getEverythingNews(
        @Query("page") page : Int
    ) : ArticleResponse

}