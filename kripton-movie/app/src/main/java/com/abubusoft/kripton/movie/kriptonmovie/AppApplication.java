package com.abubusoft.kripton.movie.kriptonmovie;

import android.app.Application;

import com.abubusoft.kripton.android.KriptonLibrary;

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        KriptonLibrary.init(this);


    }
}
