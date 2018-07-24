package com.abubusoft.kripton.example.rssreader.service.persistence

enum class FilterType private constructor(sql: String) {
    ALL(""),
    UNREAD("read=0"),
    READ("read=1");

    var sql: String
        internal set

    init {
        this.sql = sql
    }
}