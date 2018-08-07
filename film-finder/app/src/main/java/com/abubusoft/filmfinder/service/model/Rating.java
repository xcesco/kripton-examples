package com.abubusoft.filmfinder.service.model;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Rating {
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String source;

    private String value;

}
