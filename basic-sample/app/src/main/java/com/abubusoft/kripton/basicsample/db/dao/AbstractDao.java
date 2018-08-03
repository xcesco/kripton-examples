package com.abubusoft.kripton.basicsample.db.dao;

import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;

interface AbstractDao<T> {

    @BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.REPLACE)
    void insert(T bean);
}
