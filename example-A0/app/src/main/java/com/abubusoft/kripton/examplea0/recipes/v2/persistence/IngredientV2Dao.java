package com.abubusoft.kripton.examplea0.recipes.v2.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.examplea0.recipes.v2.model.AuthorV2;
import com.abubusoft.kripton.examplea0.recipes.v2.model.IngredientV2;

/**
 * Created by xcesco on 01/09/2017.
 */
@BindDao(IngredientV2.class)
public interface IngredientV2Dao extends BaseV2Dao<IngredientV2> {

}
