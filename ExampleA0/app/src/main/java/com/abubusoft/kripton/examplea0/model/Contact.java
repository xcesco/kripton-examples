package com.abubusoft.kripton.examplea0.model;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.annotation.BindDisabled;
import com.abubusoft.kripton.annotation.BindType;

import java.util.Date;

/**
 * Created by xcesco on 25/08/2017.
 */

@BindType
@BindTable()
public class Contact {
    @Bind(value="guru", order = 3)
    protected String name;

    @Bind(order = 2)
    protected String surname;

    @Bind(order = 1)
    @BindAdapter(adapter=DateAdapter.class, dataType = Long.class)
    protected Date birthDay;

    protected byte[] image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
