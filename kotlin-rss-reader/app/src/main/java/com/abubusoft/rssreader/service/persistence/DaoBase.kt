package com.abubusoft.rssreader.service.persistence

import com.abubusoft.kripton.android.annotation.BindSqlInsert
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType

interface DaoBase<E> {
    @BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.IGNORE)
    fun insert(bean: E): Boolean
}