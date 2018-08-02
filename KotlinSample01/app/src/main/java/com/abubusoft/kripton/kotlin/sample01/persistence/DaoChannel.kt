package com.abubusoft.kripton.kotlin.sample01.persistence


import android.arch.lifecycle.LiveData
import com.abubusoft.kripton.android.annotation.BindDao
import com.abubusoft.kripton.android.annotation.BindSqlChildSelect
import com.abubusoft.kripton.android.annotation.BindSqlSelect
import com.abubusoft.kripton.android.sqlite.PaginatedResult
import com.abubusoft.kripton.kotlin.sample01.model.Channel


@BindDao(Channel::class)
interface DaoChannel : DaoBase<Channel> {

    @BindSqlSelect(where = "rssFeedId=:rssFeedId", childrenSelects = [BindSqlChildSelect(field = "articles", method = "selectByChannelUd")])
    fun selectByRssFeedId(rssFeedId: Long): LiveData<Channel>

    @BindSqlSelect(where = "rssFeedId=:rssFeedId", pageSize = 20)
    fun selectOneByRssFeedId(rssFeedId: Long): Channel

    @BindSqlSelect
    fun selectOne(): LiveData<Channel>

    @BindSqlSelect(where ="id in (:ids)")
    fun selectOne(ids: Array<Long>): LiveData<Channel>
}