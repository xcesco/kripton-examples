package com.abubusoft.kripton.example.rssreader.service.persistence

import android.arch.lifecycle.LiveData
import com.abubusoft.kripton.android.annotation.BindDao
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere
import com.abubusoft.kripton.android.annotation.BindSqlSelect
import com.abubusoft.kripton.android.annotation.BindSqlUpdate
import com.abubusoft.kripton.example.rssreader.service.model.Article

@BindDao(Article::class)
interface DaoArticle {

    @BindSqlUpdate(where = "id=:id")
    fun update(id: Long, read: Boolean)

    @BindSqlSelect
    fun selectByChannel(@BindSqlDynamicWhere where: String): LiveData<List<Article>>

    @BindSqlSelect(where = "id=:id")
    fun selectById(id: Long): List<Article>

    @BindSqlSelect(where = "id in (:ids)")
    fun selectByChannelUd(ids: List<Long>): List<Article>

    @BindSqlSelect(where = "guid=:guid")
    fun selectByGuid(guid: String): Article
}