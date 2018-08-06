package com.abubusoft.kripton.examplea0.recipes.v3.persistence;

import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.examplea0.recipes.v3.model.IngredientV3;

/**
 * Created by xcesco on 01/09/2017.
 */
@BindContentProviderPath(path = "ingredients")
@BindDao(IngredientV3.class)
public interface IngredientV3Dao extends BaseV3Dao<IngredientV3> {

}
