package com.abubusoft.kripton.examplea0.recipes.v2.persistence;

import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.examplea0.recipes.v2.model.RecipeV2;

import java.util.List;

/**
 * Created by xcesco on 01/09/2017.
 */

public interface BaseV2Dao<E> {
    @BindSqlSelect
    List<E> readAll();

    @BindSqlInsert
    int insert(E item);
}
