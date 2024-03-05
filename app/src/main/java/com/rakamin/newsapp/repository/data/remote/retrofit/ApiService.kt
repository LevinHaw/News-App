package com.rakamin.newsapp.repository.data.remote.retrofit

import com.rakamin.newsapp.repository.data.remote.response.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getHeadlineNews(
        @Query("country") country : String,
        @Query("category") category : String,
        @Query("page") page : Int
    ) : ArticleResponse

    @GET("v2/everything")
    suspend fun getEverythingNews(
        @Query("q") q : String,
        @Query("sortBy") sortBy : String,
        @Query("page") page : Int
    ) : ArticleResponse

}