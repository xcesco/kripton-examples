package com.abubusoft.kripton.movie.kriptonmovie.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.movie.kriptonmovie.model.MovieWithDirector;
import com.abubusoft.kripton.movie.kriptonmovie.persistence.MovieRepository;

import java.util.List;

import javax.inject.Inject;

public class MoviesViewModel extends AndroidViewModel {
    private final MovieRepository repository;
    private LiveData<List<MovieWithDirector>> moviesLiveData;

    @Inject
    public MoviesViewModel(@NonNull MovieRepository movieRepository) {
        super((Application)KriptonLibrary.getContext());

        repository =movieRepository;
        moviesLiveData = repository.getAllMovies();
    }

    public LiveData<List<MovieWithDirector>> getMoviesList() {
        return moviesLiveData;
    }

    public void deleteAll() {
        repository.deleteMovies();
    }

    public void insertOrUpdateMovie(String movieTitle, String movieTitleExtra, String movieDirectorFullName) {
        repository.insertOrUpdateMovie(movieTitle, movieTitleExtra, movieDirectorFullName);
    }

    public void clearDb() {
        repository.clearDb();
    }
}