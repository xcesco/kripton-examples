package com.abubusoft.kripton.movie.kriptonmovie.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.movie.kriptonmovie.model.Director;
import com.abubusoft.kripton.movie.kriptonmovie.persistence.MovieRepository;

import java.util.List;

import javax.inject.Inject;

public class DirectorsViewModel extends AndroidViewModel {

    private final MovieRepository repository;
    private LiveData<List<Director>> directorsLiveData;

    @Inject
    public DirectorsViewModel(MovieRepository movieRepository) {
        super((Application) KriptonLibrary.getContext());

        repository=movieRepository;
        directorsLiveData = repository.getAllDirectors();
    }

    public LiveData<List<Director>> getDirectorList() {
        return directorsLiveData;
    }

    public void deleteAll() {
        repository.deleteDirectors();
    }

    public void insertOrUpdateDirector(String directorFullNameExtra, String fullName) {
        repository.insertOrUpdateDirector(directorFullNameExtra, fullName);
    }

}