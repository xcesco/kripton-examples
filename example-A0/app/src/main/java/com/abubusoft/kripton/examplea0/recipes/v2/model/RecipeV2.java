package com.abubusoft.kripton.examplea0.recipes.v2.model;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xcesco on 01/09/2017.
 */
@BindType
@BindTable(name="recipes")
public class RecipeV2 extends Entity {
    public String name;


    @BindColumn(foreignKey = AuthorV2.class)
    public long authorId;
}
