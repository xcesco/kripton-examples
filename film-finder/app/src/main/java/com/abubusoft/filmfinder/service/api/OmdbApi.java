package com.abubusoft.filmfinder.service.api;

import com.abubusoft.filmfinder.service.model.FilmDetail;
import com.abubusoft.filmfinder.service.model.Search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbApi {

    // http://www.omdbapi.com/?s=star&apikey=d497e644

    @GET
    Call<Search> search(@Query("s") String search, @Query("apikey") String apiKey);

    @GET
    Call<FilmDetail> getFilm(@Query("i") String uid, @Query("apikey") String apiKey);

}
