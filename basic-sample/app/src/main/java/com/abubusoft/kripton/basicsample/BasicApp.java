package com.abubusoft.kripton.basicsample;

import android.app.Application;

import com.abubusoft.kripton.basicsample.db.BindAppDataSource;

/**
 * Android Application class. Used for accessing singletons.
 */
public class BasicApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

    }

    public BindAppDataSource getRepository() {
        return BindAppDataSource.getInstance();
    }
}