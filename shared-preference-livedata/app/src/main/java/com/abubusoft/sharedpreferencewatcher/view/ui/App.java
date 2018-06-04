package com.abubusoft.sharedpreferencewatcher.view.ui;

import android.app.Application;

import com.abubusoft.kripton.android.KriptonLibrary;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        KriptonLibrary.init(this);
    }
}
