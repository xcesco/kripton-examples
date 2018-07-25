package com.abubusoft.kripton.kotlin.sample01.persistence

import com.abubusoft.kripton.android.annotation.BindSqlInsert
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType

interface DaoBase<E> {
    @BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.IGNORE)
    fun insert(bean: E): Boolean
}