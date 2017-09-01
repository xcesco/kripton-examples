package com.abubusoft.kripton.examplea0.recipes.v3.model;

import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.examplea0.model.DateAdapter;

import java.util.Date;

/**
 * Created by xcesco on 01/09/2017.
 */

public class Entity {
    public long id;

    @BindAdapter(adapter=DateAdapter.class, dataType = Long.class)
    public Date creationTime;

}
