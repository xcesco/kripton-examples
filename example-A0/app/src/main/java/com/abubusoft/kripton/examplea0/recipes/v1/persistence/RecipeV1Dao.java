package com.abubusoft.kripton.examplea0.recipes.v1.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.examplea0.recipes.v1.model.RecipeV1;

import java.util.List;

/**
 * Created by xcesco on 01/09/2017.
 */
@BindDao(RecipeV1.class)
public interface RecipeV1Dao {
    @BindSqlSelect
    List<RecipeV1> readAll();

    @BindSqlInsert
    int insert(RecipeV1 item);
}
