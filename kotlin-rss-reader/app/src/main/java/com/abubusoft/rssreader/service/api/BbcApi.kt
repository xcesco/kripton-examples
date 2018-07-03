package com.abubusoft.rssreader.service.api

import com.abubusoft.rssreader.service.model.RssFeed
import retrofit2.Call
import retrofit2.http.GET

interface BbcApi {
    @GET("news/rss.xml?edition=int")
    fun loadRSSFeed(): Call<RssFeed>
}