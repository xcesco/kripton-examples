package com.abubusoft.kripton.samples.paging2;

import android.app.Application;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.samples.paging2.BindCheeseDataSource;

public class SampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        KriptonLibrary.init(this);

        dataSource= BindCheeseDataSource.getInstance();
    }

    public BindCheeseDataSource getDataSource() {
        return dataSource;
    }

    private BindCheeseDataSource dataSource;
}
