package com.abubusoft.kripton.movie.kriptonmovie.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.movie.kriptonmovie.model.Director;
import com.abubusoft.kripton.movie.kriptonmovie.persistence.BindMovieDataSource;

import java.util.List;

public class DirectorsViewModel extends AndroidViewModel {

    private LiveData<List<Director>> directorsLiveData;

    public DirectorsViewModel(Application application) {
        super(application);

        directorsLiveData = BindMovieDataSource.getInstance().getDirectorDao().getAllDirectors();
    }

    public LiveData<List<Director>> getDirectorList() {
        return directorsLiveData;
    }

    public void insert(List<Director> directors)  {
        BindMovieDataSource.getInstance().executeAsync(daoFactory -> {
            daoFactory.getDirectorDao().insert(directors);
           return TransactionResult.COMMIT;
        });
    }

    public void update(Director director)
    {
        BindMovieDataSource.getInstance().executeAsync(daoFactory -> {
            daoFactory.getDirectorDao().update(director);
            return TransactionResult.COMMIT;
        });
    }

    public void deleteAll() {
        BindMovieDataSource.getInstance().executeAsync(daoFactory -> {
            daoFactory.getDirectorDao().deleteAll();
            return TransactionResult.COMMIT;
        });
    }
}