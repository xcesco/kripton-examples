package com.abubusoft.filmfinder.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.abubusoft.filmfinder.FilmFinderApplication;
import com.abubusoft.filmfinder.service.model.Search;
import com.abubusoft.filmfinder.service.repository.FilmRepository;
import com.abubusoft.kripton.android.KriptonLibrary;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;

// https://github.com/santhoshvai/omdb-android-client/blob/master/app/src/main/res/layout/list_item_movie.xml
public class FilmViewModel {
    private final FilmRepository repository;
    private final ExecutorService executionService;
    private final LiveData<Search> films;
    private final MutableLiveData<String> filter = new MutableLiveData<String>();

    public FilmViewModel() {
        executionService = KriptonLibrary.getExecutorService();
        repository = new FilmRepository();
        films = Transformations.switchMap(filter, v -> repository.findFilm(v));
    }

    public LiveData<Search> getFilms() {
        return films;
    }

    public void setSearchFilter(String searchFilter) {
        filter.setValue(searchFilter);
    }
}
