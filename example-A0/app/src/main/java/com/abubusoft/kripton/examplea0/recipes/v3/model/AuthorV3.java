package com.abubusoft.kripton.examplea0.recipes.v3.model;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

/**
 * Created by xcesco on 01/09/2017.
 */
@BindType
@BindTable(name="authors", uniqueIndexes = {"name, surname asc", "email"})
public class AuthorV3 extends Entity {

    @BindColumn(nullable = false)
    public String name;

    @BindColumn(nullable = false)
    public String surname;

    @BindColumn(nullable = false)
    public String email;
}
