package com.abubusoft.kripton.examples.rssreader.api;

import com.abubusoft.kripton.examples.rssreader.model.RSSFeed;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BbcApi {
    @GET("news/rss.xml?edition=int")
    Call<RSSFeed> loadRSSFeed();
}
