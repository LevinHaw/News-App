package com.rakamin.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rakamin.newsapp.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ArticleRepository) : ViewModel() {

    val listArticle = repository.getArticle().cachedIn(viewModelScope)

    val listHeadline = repository.getHeadline().cachedIn(viewModelScope)

}