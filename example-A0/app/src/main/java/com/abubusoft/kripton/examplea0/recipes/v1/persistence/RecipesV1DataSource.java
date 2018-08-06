package com.abubusoft.kripton.examplea0.recipes.v1.persistence;

import com.abubusoft.kripton.android.annotation.BindDataSource;

/**
 * Created by xcesco on 01/09/2017.
 */
@BindDataSource(fileName = "recipes.db", version=1, daoSet = {RecipeV1Dao.class}, generateSchema = true)
public interface RecipesV1DataSource {
}
