package com.abubusoft.kripton.examplea0.model;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindDisabled;
import com.abubusoft.kripton.annotation.BindType;

import java.util.List;
import java.util.Map;

/**
 * Created by xcesco on 29/08/2017.
 */
@BindType
public class Country {

    @Bind(mapKeyName = "uid", mapValueName = "city")
    public Map<String, City> cities;

    @Bind("langs")
    public List<Language> spokenLanguages;
}
