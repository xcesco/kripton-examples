package com.abubusoft.kripton.example.rssreader.service.model

import com.abubusoft.kripton.android.ColumnType
import com.abubusoft.kripton.android.annotation.BindSqlColumn
import com.abubusoft.kripton.android.annotation.BindSqlType
import com.abubusoft.kripton.annotation.*
import java.net.URL

@BindType("item")
@BindXmlType(namespaces = arrayOf(BindXmlNamespace(prefix = "dc", uri = "http://purl.org/dc/elements/1.1/"), BindXmlNamespace(prefix = "content", uri = "http://purl.org/dc/elements/1.1/")))
@BindSqlType(name = "articles")
data class Article(
        @BindSqlColumn(columnType = ColumnType.PRIMARY_KEY) val id: Long = 0,

        val title: String? = null,
        val description: String? = null,
        val link: URL? = null,
        val author: String? = null,

        @BindSqlColumn(nullable = false, columnType = ColumnType.UNIQUE)
        val guid: String? = null,

        val comments: URL? = null,

        @BindSqlColumn(parentEntity = Channel::class)
        var channelId: Long = 0,

        @Bind("thumbnail")
        @BindXml(namespace = "media")
        val thumbnail: Thumbnail? = null,

        val read: Boolean = false)