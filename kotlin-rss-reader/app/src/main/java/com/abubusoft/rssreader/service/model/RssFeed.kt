package com.abubusoft.rssreader.service.model

import com.abubusoft.kripton.android.ColumnType
import com.abubusoft.kripton.android.annotation.BindSqlColumn
import com.abubusoft.kripton.android.annotation.BindSqlRelation
import com.abubusoft.kripton.annotation.Bind
import com.abubusoft.kripton.annotation.BindType
import com.abubusoft.kripton.annotation.BindXml
import com.abubusoft.kripton.xml.XmlType

@BindType(value = "rss")
class RssFeed : Entity() {

    @BindSqlColumn(columnType = ColumnType.UNIQUE)
    var uid: String? = null

    @BindXml(xmlType = XmlType.ATTRIBUTE)
    var version: String? = null

    @Bind("channel")
    @BindSqlRelation(foreignKey = "rssFeedId")
    var channels: List<Channel>? = null

}