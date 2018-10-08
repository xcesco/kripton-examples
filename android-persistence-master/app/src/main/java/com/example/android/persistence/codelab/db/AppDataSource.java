package com.example.android.persistence.codelab.db;

import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindDataSourceOptions;
import com.abubusoft.kripton.android.annotation.BindSqlAdapter;
import com.abubusoft.kripton.android.sqlite.adapters.DateMillisecondsTypeAdapter;
import com.example.android.persistence.codelab.db.utils.DataSourcePopulator;


@BindDataSourceOptions(inMemory = true, populator = DataSourcePopulator.class)
@BindDataSource(fileName = "app.db", version = 1, daoSet = {BookDao.class, LoanDao.class, UserDao.class},
        typeAdapters = {@BindSqlAdapter(adapter = DateMillisecondsTypeAdapter.class)},
        asyncTask = true
        )
public interface AppDataSource {


}

