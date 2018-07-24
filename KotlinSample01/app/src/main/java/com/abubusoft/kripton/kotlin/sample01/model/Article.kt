package com.abubusoft.kripton.example.rssreader.service.model

import com.abubusoft.kripton.android.ColumnType
import com.abubusoft.kripton.android.annotation.BindSqlColumn
import com.abubusoft.kripton.android.annotation.BindSqlType
import com.abubusoft.kripton.annotation.*
import java.net.URL

@BindSqlType(name = "articles")
data class Article(
        @BindSqlColumn(columnType = ColumnType.PRIMARY_KEY)
        val id: Long,

        @BindSqlColumn(columnType = ColumnType.UNIQUE)
        val guid: String,
        val title: String?,
        val description: String?,
        val link: URL?,
        val read: Boolean
)