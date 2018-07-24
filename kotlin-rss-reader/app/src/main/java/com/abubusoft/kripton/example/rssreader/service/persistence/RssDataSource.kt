package com.abubusoft.kripton.example.rssreader.service.persistence

import com.abubusoft.kripton.android.annotation.BindDataSource


@BindDataSource(fileName = "rss.db", daoSet = [DaoRss::class, DaoArticle::class, DaoChannel::class], asyncTask = true)
interface RssDataSource
