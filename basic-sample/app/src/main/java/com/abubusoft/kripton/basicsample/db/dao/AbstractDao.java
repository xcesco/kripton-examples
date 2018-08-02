package com.abubusoft.kripton.basicsample.db.dao;

import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.basicsample.db.dao.entity.CommentEntity;

interface AbstractDao<T>  {

    @BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.REPLACE)
    void insert(T bean);
}
