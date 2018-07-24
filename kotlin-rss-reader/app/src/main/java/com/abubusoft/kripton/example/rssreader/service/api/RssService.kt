package com.abubusoft.kripton.example.rssreader.service.api

import com.abubusoft.kripton.BinderType
import com.abubusoft.kripton.example.rssreader.service.model.RssFeed
import com.abubusoft.kripton.retrofit2.KriptonBinderConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit

class RssService {

    internal var bbcApi: BbcApi

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)  // <-- this is the important line!

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(KriptonBinderConverterFactory.create(BinderType.XML))
                .client(httpClient.build()).build()

        bbcApi = retrofit.create(BbcApi::class.java)
    }

    fun execute(): Call<RssFeed> {
        return bbcApi.loadRSSFeed()
    }

    companion object {

        val RSS_NAME = "bbc"

        //static final String BASE_URL = "http://vogella.com/";
        internal val BASE_URL = "http://feeds.bbci.co.uk/"
    }
}