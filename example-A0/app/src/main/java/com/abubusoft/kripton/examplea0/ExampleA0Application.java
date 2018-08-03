package com.abubusoft.kripton.examplea0;

import android.app.Application;

import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;

/**
 * Created by xcesco on 28/08/2017.
 */

public class ExampleA0Application extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        KriptonLibrary.init(this);

       // BindStudentsDataSource.build(DataSourceOptions.builder().build());

       // KriptonBinder.bind(BinderType.SMILE);

    }
}
