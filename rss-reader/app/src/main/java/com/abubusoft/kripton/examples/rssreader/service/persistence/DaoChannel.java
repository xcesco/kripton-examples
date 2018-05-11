package com.abubusoft.kripton.examples.rssreader.service.persistence;

import android.arch.lifecycle.LiveData;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlChildSelect;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.examples.rssreader.service.model.Channel;
import com.abubusoft.kripton.examples.rssreader.service.model.RssFeed;

import java.util.List;

@BindDao(Channel.class)
public interface DaoChannel extends DaoBase<Channel> {

    @BindSqlSelect(where="rssFeedId=${rssFeedId}", childrenSelects = {@BindSqlChildSelect(field = "articles", method = "selectByChannelUd")})
    List<Channel> selectByRssFeedId(long rssFeedId);

    @BindSqlSelect
    LiveData<Channel> selectOne();
}
