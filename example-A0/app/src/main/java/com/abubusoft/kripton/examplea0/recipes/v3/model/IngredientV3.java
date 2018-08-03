package com.abubusoft.kripton.examplea0.recipes.v3.model;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

/**
 * Created by xcesco on 01/09/2017.
 */

@BindType
@BindTable(name="ingredients")
public class IngredientV3 extends Entity {
    public String description;
    public int quantity;

    @BindColumn(foreignKey = RecipeV3.class)
    public long recipeId;
}
