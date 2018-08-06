package com.abubusoft.kripton.examplea0.recipes.v1.model;

import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xcesco on 01/09/2017.
 */
@BindType
@BindTable(name="recipes")
public class RecipeV1 {
    public long id;
    public String name;
    public List<String> ingredients=new ArrayList<String>();
}
