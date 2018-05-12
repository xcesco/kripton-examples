package com.abubusoft.kripton.examples.rssreader.service.persistence;

import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;

public interface DaoBase<E> {
    @BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.IGNORE)
    boolean insert(E bean);
}
