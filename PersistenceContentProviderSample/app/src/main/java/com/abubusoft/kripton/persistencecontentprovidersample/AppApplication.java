package com.abubusoft.kripton.persistencecontentprovidersample;

import android.app.Application;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.persistencecontentprovidersample.data.BindSampleDataSource;

/**
 * Created by xcesco on 25/02/2018.
 */

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        KriptonLibrary.init(this);

        BindSampleDataSource.instance();
    }
}
