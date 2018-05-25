package com.abubusoft.filmfinder.service.api;

import android.content.Context;

import com.abubusoft.filmfinder.R;
import com.abubusoft.filmfinder.service.model.FilmDetail;
import com.abubusoft.filmfinder.service.model.Search;
import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.retrofit2.KriptonBinderConverterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;

public class FilmService {

    private final String apiKey;
    OmdbApi api;

    static final String BASE_URL = "http://www.omdbapi.com/";

    public FilmService(Context context) {
        apiKey=context.getResources().getString(R.string.omdb_api_key);
        
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(KriptonBinderConverterFactory.create())
                .client(httpClient.build()).build();

        api = retrofit.create(OmdbApi.class);
    }

    public Call<Search> executeSearch(String search) {
        Call<Search> call = api.search(search, this.apiKey);
        return call;
    }

    public Call<FilmDetail> executeGetById(String uid) {
        Call<FilmDetail> call = api.getFilm(uid, this.apiKey);
        return call;
    }
}
