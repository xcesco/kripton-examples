package com.abubusoft.kripton.example.rssreader.service.api


import com.abubusoft.kripton.example.rssreader.service.model.RssFeed
import retrofit2.Call
import retrofit2.http.GET

interface VogellaApi {

    @GET("article.rss")
    fun loadRSSFeed(): Call<RssFeed>
}