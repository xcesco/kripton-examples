package com.abubusoft.kripton.examples.rssreader.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.examples.rssreader.model.Article;
import com.abubusoft.kripton.examples.rssreader.model.Channel;

@BindDao(Channel.class)
public interface DaoChannel extends DaoBase<Channel> {
}
