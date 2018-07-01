package com.abubusoft.rssreader.service.persistence

import com.abubusoft.kripton.android.annotation.BindDataSource

@BindDataSource(fileName = "rss.db", daoSet = arrayOf(DaoRss::class, DaoArticle::class, DaoChannel::class), asyncTask = true)
interface RssDataSource