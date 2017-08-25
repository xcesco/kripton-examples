package com.abubusoft.kripton.examplea0.model;

import java.util.Date;

/**
 * Created by xcesco on 25/08/2017.
 */

public class Contact {
    protected String name;
    protected String surname;
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
