package com.abubusoft.kripton.examplea0.recipes.v2.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.examplea0.recipes.v2.model.RecipeV2;

import java.util.List;

/**
 * Created by xcesco on 01/09/2017.
 */
@BindDao(RecipeV2.class)
public interface RecipeV2Dao extends BaseV2Dao<RecipeV2> {

}
