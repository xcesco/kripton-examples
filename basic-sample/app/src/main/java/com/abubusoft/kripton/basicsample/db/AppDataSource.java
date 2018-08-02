package com.abubusoft.kripton.basicsample.db;

import android.support.annotation.VisibleForTesting;

import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindDataSourceOptions;
import com.abubusoft.kripton.android.annotation.BindSqlAdapter;
import com.abubusoft.kripton.android.sqlite.adapters.DateMillisecondsTypeAdapter;
import com.abubusoft.kripton.basicsample.db.dao.AppDatabasePopulator;
import com.abubusoft.kripton.basicsample.db.dao.CommentDao;
import com.abubusoft.kripton.basicsample.db.dao.ProductDao;

@BindDataSourceOptions(populator = AppDatabasePopulator.class)
@BindDataSource(fileName = "basic-sample-db",
        version = 1,
        daoSet = {ProductDao.class, CommentDao.class},

        typeAdapters = {
            @BindSqlAdapter(adapter = DateMillisecondsTypeAdapter.class)
        })
public interface AppDataSource {

}