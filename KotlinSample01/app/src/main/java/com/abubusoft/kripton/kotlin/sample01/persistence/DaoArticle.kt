package com.abubusoft.kripton.kotlin.sample01.persistence

import android.arch.lifecycle.LiveData
import com.abubusoft.kripton.android.annotation.BindDao
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere
import com.abubusoft.kripton.android.annotation.BindSqlSelect
import com.abubusoft.kripton.android.annotation.BindSqlUpdate
import com.abubusoft.kripton.kotlin.sample01.model.Article

@BindDao(Article::class)
interface DaoArticle : DaoBase<Article> {

    @BindSqlUpdate(where = "id=:id")
    fun update(id: Long, channelId: Long, read: Boolean)

    @BindSqlSelect
    fun selectByChannel(@BindSqlDynamicWhere where: String): LiveData<List<Article>>

    @BindSqlSelect(where = "channelId=:channelId")
    fun selectByChannelUd(channelId: Long): List<Article>

    @BindSqlSelect(where = "channelId=:channelId AND guid=:guid")
    fun selectByGuid(channelId: Long, guid: String): Article
}