package com.abubusoft.filmfinder.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.abubusoft.filmfinder.FilmFinderApplication;
import com.abubusoft.filmfinder.service.model.Search;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;

public class FilmRepository {

    public LiveData<Search> findFilm(String search) {
        MutableLiveData<Search> result = new MutableLiveData<>();

        KriptonLibrary.getExecutorService().execute(() -> {
            Logger.info("aaaa ");
            result.postValue(FilmFinderApplication.filmService.executeSearch(search));
        });


        return result;
    }
}
