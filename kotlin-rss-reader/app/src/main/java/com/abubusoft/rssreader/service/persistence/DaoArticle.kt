package com.abubusoft.rssreader.service.persistence

import android.arch.lifecycle.MutableLiveData
import com.abubusoft.kripton.android.annotation.BindDao
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere
import com.abubusoft.kripton.android.annotation.BindSqlSelect
import com.abubusoft.kripton.android.annotation.BindSqlUpdate
import com.abubusoft.rssreader.service.model.Article

@BindDao(Article::class)
interface DaoArticle : DaoBase<Article> {

    @BindSqlUpdate(where = "id=\${id}")
    fun update(id: Long, channelId: Long, read: Boolean)

    @BindSqlSelect
    fun selectByChannel(@BindSqlDynamicWhere where: String): MutableLiveData<List<Article>>

    @BindSqlSelect(where = "channelId=\${channelId}")
    fun selectByChannelUd(channelId: Long): List<Article>

    @BindSqlSelect(where = "channelId=\${channelId} AND guid=\${guid}")
    fun selectByGuid(channelId: Long, guid: String): Article
}