package com.abubusoft.rssreader.service.persistence

import com.abubusoft.kripton.android.annotation.BindDao
import com.abubusoft.kripton.android.annotation.BindSqlSelect
import com.abubusoft.rssreader.service.model.RssFeed

@BindDao(RssFeed::class)
interface DaoRss : DaoBase<RssFeed> {

    @BindSqlSelect(where = "uid=\${uid}")
    fun selectOne(uid: String): RssFeed

}