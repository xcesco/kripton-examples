package com.abubusoft.kripton.samples.paging2;

import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType
public class Cheese {
    private long id;

    public Cheese(long id, String name, byte[] picture) {
        this.id = id;
        this.name = name;
        this.picture=picture;
    }

    public Cheese(String name, byte[] picture) {
        this.id = -1;
        this.name = name;
        this.picture=picture;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private String name;

    public byte[] getPicture() {
        return picture;
    }

    private byte[] picture;
}
