package com.abubusoft.kripton.basicsample.repository.db;

import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindDataSourceOptions;
import com.abubusoft.kripton.android.annotation.BindSqlAdapter;
import com.abubusoft.kripton.android.sqlite.adapters.DateMillisecondsTypeAdapter;
import com.abubusoft.kripton.basicsample.repository.db.dao.CommentDao;
import com.abubusoft.kripton.basicsample.repository.db.dao.ProductDao;

@BindDataSourceOptions(populator = AppDataSourcePopulator.class)
@BindDataSource(version = 1,
        daoSet = {CommentDao.class, ProductDao.class},
        fileName = "app.db",
        typeAdapters = {
            @BindSqlAdapter(adapter = DateMillisecondsTypeAdapter.class)}
)
public interface AppDataSource {
}
