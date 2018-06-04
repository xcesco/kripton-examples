package com.abubusoft.sharedpreferencewatcher.model;

import com.abubusoft.kripton.android.sharedprefs.PreferenceTypeAdapter;

import java.util.Locale;

/**
 * converts input taked by preference to upper case
 */
public class UpperAdapter implements PreferenceTypeAdapter<String, String> {
    @Override
    public String toJava(String dataValue) {
        if (dataValue != null) {
            return dataValue.toUpperCase(Locale.ENGLISH);
        }
        return null;
    }

    @Override
    public String toData(String javaValue) {
        return javaValue;
    }
}
