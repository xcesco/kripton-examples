package com.abubusoft.kripton.rxsample;

import android.app.Application;

import com.abubusoft.kripton.android.KriptonLibrary;

public class RxSampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        KriptonLibrary.init(this);
    }
}
