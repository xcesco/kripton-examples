package com.abubusoft.kripton.movie.kriptonmovie.persistence;

import com.abubusoft.kripton.android.sqlite.SQLitePopulator;

class MoviePopulator implements SQLitePopulator {
    @Override
    public void execute() {
        BindMovieDataSource.getInstance().clearDbAsync();
    }
}
