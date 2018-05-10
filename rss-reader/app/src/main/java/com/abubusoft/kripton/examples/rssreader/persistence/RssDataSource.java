package com.abubusoft.kripton.examples.rssreader.persistence;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(fileName = "rss.db",daoSet = {DaoArticle.class, DaoChannel.class}, asyncTask = true)
public interface RssDataSource {
}
