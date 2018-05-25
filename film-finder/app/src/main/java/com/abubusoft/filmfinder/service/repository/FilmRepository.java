package com.abubusoft.filmfinder.service.repository;

import android.arch.lifecycle.LiveData;

import com.abubusoft.filmfinder.FilmFinderApplication;
import com.abubusoft.filmfinder.service.model.Search;
import com.abubusoft.kripton.android.KriptonLibrary;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

public class FilmRepository {

    public Search findFilm(String search) {
        try {
            return FilmFinderApplication.filmService.executeSearch(search).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
