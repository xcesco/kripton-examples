package com.abubusoft.rssreader.service.persistence

import com.abubusoft.kripton.android.annotation.BindDataSource
import com.abubusoft.rssreader.service.persistence.BindRssDataSource.*

@BindDataSource(fileName = "rss.db", daoSet = [DaoRss::class, DaoArticle::class, DaoChannel::class], asyncTask = true)
interface RssDataSource


fun BindRssDataSource.execute(transition: Transaction) {
    return getInstance().execute(transition)
}