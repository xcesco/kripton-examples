package com.abubusoft.kripton.kotlin.sample01.persistence


import android.arch.lifecycle.LiveData
import com.abubusoft.kripton.android.annotation.BindDao
import com.abubusoft.kripton.android.annotation.BindSqlChildSelect
import com.abubusoft.kripton.android.annotation.BindSqlSelect
import com.abubusoft.kripton.kotlin.sample01.model.Channel


@BindDao(Channel::class)
interface DaoChannel : DaoBase<Channel> {

    @BindSqlSelect(where = "rssFeedId=:rssFeedId", childrenSelects = [BindSqlChildSelect(field = "articles", method = "selectByChannelUd")])
    fun selectByRssFeedId(rssFeedId: Long): List<Channel>

    @BindSqlSelect(where = "rssFeedId=:rssFeedId")
    fun selectOneByRssFeedId(rssFeedId: Long): Channel

    @BindSqlSelect
    fun selectOne(): LiveData<Channel>
}