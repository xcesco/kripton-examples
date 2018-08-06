package com.abubusoft.kripton.examplea0.recipes.v3.persistence;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import java.util.List;

/**
 * Created by xcesco on 01/09/2017.
 */

public interface BaseV3Dao<E> {
    @BindContentProviderEntry
    @BindSqlSelect
    List<E> readAll();

    @BindContentProviderEntry
    @BindSqlInsert
    int insert(E item);
}
