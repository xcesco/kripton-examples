package com.abubusoft.kripton.kotlin.sample01.model

import com.abubusoft.kripton.android.annotation.BindSqlRelation
import com.abubusoft.kripton.annotation.Bind
import com.abubusoft.kripton.android.annotation.BindSqlColumn
import com.abubusoft.kripton.annotation.BindAdapter
import com.abubusoft.kripton.android.ColumnType
import com.abubusoft.kripton.android.annotation.BindSqlType
import com.abubusoft.kripton.annotation.BindType
import java.util.*

@BindType
@BindSqlType(name = "channels")
data class Channel(
        @BindSqlColumn(columnType = ColumnType.PRIMARY_KEY)
        var id: Long = 0,

        var title: String? = null,

        @BindSqlColumn(columnType = ColumnType.UNIQUE)
        var link: String? = null,

        var description: String? = null,
        var language: String? = null,
        var copyright: String? = null,

        @BindAdapter(adapter = DateAdapter::class)
        var pubDate: Date? = null,

        @BindAdapter(adapter = DateAdapter::class)
        var lastBuildDate: Date? = null,

        var image: Image? = null,

        @BindSqlColumn(parentEntity = RssFeed::class)
        var rssFeedId: Long = 0,

        @Bind("item")
        @BindSqlRelation(foreignKey = "channelId")
        var articles: List<Article>? = null
)