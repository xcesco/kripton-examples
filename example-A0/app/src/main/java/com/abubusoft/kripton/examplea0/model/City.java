package com.abubusoft.kripton.examplea0.model;

import com.abubusoft.kripton.annotation.BindType;

/**
 * Created by xcesco on 29/08/2017.
 */
@BindType
public class City {

    public City() {

    }

    public City(String value) {
        this.name=value;
    }

    public String name;
}
