package com.abubusoft.kripton.kotlin.sample01.model

import com.abubusoft.kripton.android.ColumnType
import com.abubusoft.kripton.android.annotation.BindSqlColumn
import com.abubusoft.kripton.android.annotation.BindSqlRelation
import com.abubusoft.kripton.android.annotation.BindSqlType
import com.abubusoft.kripton.annotation.Bind
import com.abubusoft.kripton.annotation.BindType
import com.abubusoft.kripton.annotation.BindXml
import com.abubusoft.kripton.xml.XmlType

@BindType(value = "rss")
@BindSqlType(name = "rss")
data class RssFeed(
        @BindSqlColumn(columnType = ColumnType.PRIMARY_KEY)
        val id: Long = 0,

        @BindSqlColumn(columnType = ColumnType.UNIQUE)
        val uid: String? = null,

        @BindXml(xmlType = XmlType.ATTRIBUTE)
        val version: String? = null,

        @Bind("channel")
        @BindSqlRelation(foreignKey = "rssFeedId")
        val channels: List<Channel>? = null
)