package com.abubusoft.kripton.samples.paging2;

import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType
public class Cheese {
    private long id;

    public Cheese(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Cheese(String name) {
        this.id = -1;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private String name;
}
