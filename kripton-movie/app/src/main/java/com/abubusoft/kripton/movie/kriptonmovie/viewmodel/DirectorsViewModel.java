package com.abubusoft.kripton.movie.kriptonmovie.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.abubusoft.kripton.movie.kriptonmovie.model.Director;
import com.abubusoft.kripton.movie.kriptonmovie.persistence.MovieRepository;

import java.util.List;

public class DirectorsViewModel extends AndroidViewModel {

    private final MovieRepository repository;
    private LiveData<List<Director>> directorsLiveData;

    public DirectorsViewModel(Application application) {
        super(application);

        repository=MovieRepository.getInstance();
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