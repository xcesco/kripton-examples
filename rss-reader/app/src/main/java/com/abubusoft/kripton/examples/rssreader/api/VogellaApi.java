package com.abubusoft.kripton.examples.rssreader.api;

import com.abubusoft.kripton.examples.rssreader.model.RSSFeed;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VogellaApi {

    @GET("article.rss")
    Call<RSSFeed> loadRSSFeed();
}