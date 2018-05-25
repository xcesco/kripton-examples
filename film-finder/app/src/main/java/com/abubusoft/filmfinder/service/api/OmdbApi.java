package com.abubusoft.filmfinder.service.api;

import com.abubusoft.filmfinder.service.model.FilmDetail;
import com.abubusoft.filmfinder.service.model.Search;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface OmdbApi {

    // http://www.omdbapi.com/?s=star&apikey=d497e644

    @Streaming
    @GET("./")
    @Headers({"Cache-control: no-cache"})
    Call<Search> search(@Query("s") String search, @Query("apikey") String apiKey);

    @Streaming
    @GET("./")
    @Headers({"Cache-control: no-cache"})
    Call<FilmDetail> getFilm(@Query("i") String uid, @Query("apikey") String apiKey);
}
