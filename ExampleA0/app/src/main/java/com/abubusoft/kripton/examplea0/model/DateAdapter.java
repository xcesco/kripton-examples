package com.abubusoft.kripton.examplea0.model;

import com.abubusoft.kripton.BindTypeAdapter;

import java.util.Date;

/**
 * Created by xcesco on 29/08/2017.
 */

public class DateAdapter implements BindTypeAdapter<Date, Long> {

    @Override
    public Date toJava(Long dataValue) throws Exception {
        if (dataValue==null) return null;
        return new Date(dataValue);
    }

    @Override
    public Long toData(Date javaValue) throws Exception {
        if (javaValue==null) return null;
        return javaValue.getTime();
    }
}
