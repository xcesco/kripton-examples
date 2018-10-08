package com.abubusoft.kripton.movie.kriptonmovie.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.movie.kriptonmovie.model.Movie;
import com.abubusoft.kripton.movie.kriptonmovie.model.MovieWithDirector;
import com.abubusoft.kripton.movie.kriptonmovie.persistence.BindMovieDataSource;

import java.util.List;

public class MoviesViewModel extends AndroidViewModel {
    private LiveData<List<MovieWithDirector>> moviesLiveData;

    public MoviesViewModel(@NonNull Application application) {
        super(application);
        moviesLiveData = BindMovieDataSource.getInstance().getMovieDao().getAllMovies();
    }

    public LiveData<List<MovieWithDirector>> getMoviesList() {
        return moviesLiveData;
    }

    public void insert(List<Movie> movies) {
        BindMovieDataSource.getInstance().executeAsync(daoFactory -> {
           daoFactory.getMovieDao().insert(movies);
           return TransactionResult.COMMIT;
        });
    }

    public void update(Movie movie) {
        BindMovieDataSource.getInstance().executeAsync(daoFactory -> {
            daoFactory.getMovieDao().update(movie);
            return TransactionResult.COMMIT;
        });
    }

    public void deleteAll() {
        BindMovieDataSource.getInstance().executeAsync(daoFactory -> {
            daoFactory.getMovieDao().deleteAll();
            return TransactionResult.COMMIT;
        });
    }
}