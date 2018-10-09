package com.abubusoft.kripton.movie.kriptonmovie.persistence.datasource;

import com.abubusoft.kripton.android.sqlite.SQLitePopulator;

public class MoviePopulator implements SQLitePopulator {
    @Override
    public void execute() {
        BindMovieDataSource.getInstance().clearDbAsync();
    }
}
