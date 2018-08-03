package com.abubusoft.kripton.examplea0.recipes.v3.persistence;

import com.abubusoft.kripton.android.annotation.BindContentProvider;
import com.abubusoft.kripton.android.annotation.BindDataSource;

/**
 * Created by xcesco on 01/09/2017.
 */
@BindContentProvider(authority = "com.abubusoft.examples.recipes")
@BindDataSource(fileName = "recipes.db", version=2, daoSet = {RecipeV3Dao.class, IngredientV3Dao.class, AuthorV3Dao.class}, generateSchema = true)
public interface RecipesV3DataSource {
}
