package com.abubusoft.kripton.samples.paging;

import android.app.Application;

import com.abubusoft.kripton.android.KriptonLibrary;

public class SampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        KriptonLibrary.init(this);

        dataSource=BindCheeseDataSource.getInstance();
    }

    public BindCheeseDataSource getDataSource() {
        return dataSource;
    }

    private BindCheeseDataSource dataSource;
}
