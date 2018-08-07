package com.example.android.persistence.codelab;

import android.app.Application;

import com.abubusoft.kripton.android.KriptonLibrary;

public class SampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        KriptonLibrary.init(this);

    }
}
