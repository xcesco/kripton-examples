package com.abubusoft.kripton.examples.rssreader.service.api;

import com.abubusoft.kripton.examples.rssreader.service.model.RssFeed;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VogellaApi {

    @GET("article.rss")
    Call<RssFeed> loadRSSFeed();
}