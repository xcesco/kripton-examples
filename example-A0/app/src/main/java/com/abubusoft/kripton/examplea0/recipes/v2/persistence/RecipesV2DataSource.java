package com.abubusoft.kripton.examplea0.recipes.v2.persistence;

import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.examplea0.recipes.v2.model.AuthorV2;
import com.abubusoft.kripton.examplea0.recipes.v2.model.IngredientV2;

/**
 * Created by xcesco on 01/09/2017.
 */
@BindDataSource(fileName = "recipes.db", version=2, daoSet = {RecipeV2Dao.class, IngredientV2Dao.class, AuthorV2Dao.class}, generateSchema = true)
public interface RecipesV2DataSource {
}
