package com.abubusoft.kripton.example.rssreader.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.abubusoft.kripton.android.Logger
import com.abubusoft.kripton.example.rssreader.service.model.Article
import com.abubusoft.kripton.example.rssreader.service.model.Channel
import com.abubusoft.kripton.example.rssreader.service.persistence.FilterType
import com.abubusoft.kripton.example.rssreader.service.repository.RssRepository

class RssViewModel : ViewModel() {

    val articles: LiveData<List<Article>>

    private val repository = RssRepository.instance

    val channel: LiveData<Channel>
        get() = repository.channel

    private val filterLiveData = MutableLiveData<FilterType>()

    init {
        articles = Transformations.switchMap<FilterType, List<Article>>(filterLiveData) { v -> repository.getArticleList(v) }
    }

    fun markArticleAsRead(article: Article) {
        if (!article.read) {
            repository.markArticleAsRead(article)
        } else {
            Logger.info("Article already read")
        }
    }

    fun downloadArticles() {
        repository.downloadArticles()
    }

    fun updateFilter(filter: FilterType) {
        filterLiveData.setValue(filter)
    }

}