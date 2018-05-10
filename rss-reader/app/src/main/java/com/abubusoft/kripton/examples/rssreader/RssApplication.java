package com.abubusoft.kripton.examples.rssreader;

import android.app.Application;

import com.abubusoft.kripton.android.KriptonLibrary;

public class RssApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        KriptonLibrary.init(this);
    }
}
