package com.abubusoft.kripton.examplea0.recipes.v3.persistence;

import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.examplea0.recipes.v3.model.RecipeV3;

/**
 * Created by xcesco on 01/09/2017.
 */
@BindContentProviderPath(path = "recipes")
@BindDao(RecipeV3.class)
public interface RecipeV3Dao extends BaseV3Dao<RecipeV3> {

}
