package com.abubusoft.kripton.kotlin.sample01.model

import com.abubusoft.kripton.android.ColumnType
import com.abubusoft.kripton.android.annotation.BindContentProviderEntry
import com.abubusoft.kripton.android.annotation.BindSqlColumn
import com.abubusoft.kripton.android.annotation.BindSqlType
import com.abubusoft.kripton.annotation.*
import java.net.URL

@BindType("item")
@BindXmlType(namespaces = arrayOf(BindXmlNamespace(prefix = "dc", uri = "http://purl.org/dc/elements/1.1/"), BindXmlNamespace(prefix = "content", uri = "http://purl.org/dc/elements/1.1/")))
@BindSqlType(name = "articles")
data class Article(
        @BindSqlColumn(columnType = ColumnType.PRIMARY_KEY)
        val id: Long,

        val title: String? = "test",
        val description: String?,
        val link: URL?,
        val author: String?,

        @BindSqlColumn(nullable = false, columnType = ColumnType.UNIQUE)
        val guid: String?,

        @Bind(enabled = false)
        val comments: URL?,

        @BindSqlColumn(parentEntity = Channel::class)
        val channelId: Long,

        @Bind("thumbnail")
        @BindXml(namespace = "media")
        val thumbnail: Thumbnail?,

        val read: Boolean)