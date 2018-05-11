package com.abubusoft.kripton.examples.rssreader.service.api;

import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.examples.rssreader.service.model.RssFeed;
import com.abubusoft.kripton.retrofit2.KriptonBinderConverterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;

public class RssService {

    public static final String RSS_NAME = "bbc";

    BbcApi bbcApi;

	//static final String BASE_URL = "http://vogella.com/";
	static final String BASE_URL = "http://feeds.bbci.co.uk/";

	public RssService() {
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
		logging.setLevel(HttpLoggingInterceptor.Level.BODY);

		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …

// add logging as last interceptor
		httpClient.addInterceptor(logging);  // <-- this is the important line!

		Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
				.addConverterFactory(KriptonBinderConverterFactory.create(BinderType.XML))
				.client(httpClient.build()).build();

		bbcApi = retrofit.create(BbcApi.class);
	}

	public Call<RssFeed> execute() {
		Call<RssFeed> call = bbcApi.loadRSSFeed();
		return call;
	}
}
