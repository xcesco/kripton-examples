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
        val title: String?,

        @BindSqlColumn(columnType = ColumnType.UNIQUE)
        val link: String?,

        val description: String?,
        val language: String?,
        val copyright: String?,

        @BindAdapter(adapter = DateAdapter::class)
        val pubDate: Date?,

        @BindAdapter(adapter = DateAdapter::class)
        val lastBuildDate: Date?,

        val image: Image?,

        @BindSqlColumn(parentEntity = RssFeed::class)
        val rssFeedId: Long

/*        @Bind("item")
        @BindSqlRelation(foreignKey = "channelId")
        val articles: List<Article>*/
)