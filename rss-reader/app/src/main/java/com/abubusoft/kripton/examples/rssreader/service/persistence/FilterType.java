package com.abubusoft.kripton.examples.rssreader.service.persistence;

public enum FilterType {
    ALL(""),
    UNREAD("read=0"),
    READ("read=1");

    FilterType(String sql) {
        this.sql=sql;
    }

    String sql;

    public String getSql() {
        return sql;
    }
}
