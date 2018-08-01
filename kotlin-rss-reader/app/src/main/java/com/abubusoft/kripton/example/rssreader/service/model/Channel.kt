package com.abubusoft.kripton.example.rssreader.service.model

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
        @BindSqlColumn(columnType = ColumnType.PRIMARY_KEY) var id: Long = 0,
        val title: String? = null,

        @BindSqlColumn(columnType = ColumnType.UNIQUE)
        val link: String? = null,

        val description: String? = null,
        val language: String? = null,
        val copyright: String? = null,

        @BindAdapter(adapter = DateAdapter::class)
        val pubDate: Date? = null,

        @BindAdapter(adapter = DateAdapter::class)
        val lastBuildDate: Date? = null,

        val image: Image? = null,

        @BindSqlColumn(parentEntity = RssFeed::class)
        var rssFeedId: Long = 0,

        @Bind("item")
        @BindSqlRelation(foreignKey = "channelId")
        val articles: List<Article>? = null
)