package com.abubusoft.filmfinder.service.api;

import android.content.Context;

import com.abubusoft.filmfinder.R;
import com.abubusoft.filmfinder.service.model.FilmDetail;
import com.abubusoft.filmfinder.service.model.Search;
import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.retrofit2.KriptonBinderConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.ByteString;
import retrofit2.Call;
import retrofit2.Retrofit;

public class FilmService {

    private final String apiKey;

    OmdbApi api;

    static final String BASE_URL = "http://www.omdbapi.com/";

    public FilmService(Context context) {
        apiKey = context.getResources().getString(R.string.omdb_api_key);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(KriptonBinderConverterFactory
                        .create())
                .client(httpClient.build())
                .build();

        api = retrofit.create(OmdbApi.class);
    }

    public Search executeSearch(String search) {
        //Call<Search> call = api.search(search, this.apiKey);
        Call<Search> call = api.search(search, this.apiKey);

        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Call<FilmDetail> executeGetById(String uid) {
        Call<FilmDetail> call = api.getFilm(uid, this.apiKey);
        return call;
    }
}
