package com.abubusoft.filmfinder;

import android.app.Application;

import com.abubusoft.filmfinder.service.api.FilmService;
import com.abubusoft.kripton.android.KriptonLibrary;

// https://github.com/santhoshvai/omdb-android-client/tree/master/app/src/main/res/values
public class FilmFinderApplication extends Application {

    public static FilmService filmService;

    @Override
    public void onCreate() {
        super.onCreate();

        KriptonLibrary.init(this);
        filmService=new FilmService(this);
    }
}
