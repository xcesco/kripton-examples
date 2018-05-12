package com.abubusoft.kripton.examples.rssreader.service.persistence;

public enum FilterType {
    NO_FILTER(""),
    SKIP_READ_ARTICLE("read=0"),
    READ_ARTICLE("read=1");

    FilterType(String sql) {
        this.sql=sql;
    }

    String sql;

    public String getSql() {
        return sql;
    }
}
