package com.abubusoft.rssreader.service.model

import com.abubusoft.kripton.android.ColumnType
import com.abubusoft.kripton.android.annotation.BindSqlColumn
import com.abubusoft.kripton.android.annotation.BindSqlType
import com.abubusoft.kripton.annotation.*
import java.net.URL

@BindType("item")
@BindXmlType(namespaces = arrayOf(BindXmlNamespace(prefix = "dc", uri = "http://purl.org/dc/elements/1.1/"), BindXmlNamespace(prefix = "content", uri = "http://purl.org/dc/elements/1.1/")))
@BindSqlType(name = "articles")
class Article(
        var title: String? = null,
        var description: String? = null,
        var link: URL? = null,
        var author: String? = null,

        @BindSqlColumn(nullable = false, columnType = ColumnType.UNIQUE)
        var guid: String? = null,

        var comments: URL? = null,

        @BindSqlColumn(parentEntity = Channel::class)
        var channelId: Long = 0,

        @Bind("thumbnail")
        @BindXml(namespace = "media")
        var thumbnail: Thumbnail? = null,

        var read: Boolean = false) : Entity() {
}