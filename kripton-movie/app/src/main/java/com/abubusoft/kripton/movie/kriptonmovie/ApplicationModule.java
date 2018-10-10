package com.abubusoft.kripton.movie.kriptonmovie;


import android.app.Application;
import android.content.Context;

import com.abubusoft.kripton.movie.kriptonmovie.persistence.MovieRepository;
import com.abubusoft.kripton.movie.kriptonmovie.persistence.datasource.BindMovieDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application app) {
        mApplication = app;
    }

    @Provides
    Context provideContext() {
        return mApplication;
    }


    @Singleton
    @Provides
    MovieRepository movieRepository() {
        return new MovieRepository(BindMovieDataSource.getInstance());
    }

}
