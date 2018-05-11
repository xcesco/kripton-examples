package com.abubusoft.kripton.examples.rssreader.service.persistence;

import android.arch.lifecycle.LiveData;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.examples.rssreader.service.model.Article;

import java.util.List;

@BindDao(Article.class)
public interface DaoArticle extends DaoBase<Article> {

    @BindSqlUpdate(where = "id=${id}")
    void update(long id, boolean read);

    @BindSqlSelect
    LiveData<List<Article>> selectByChannel();

    @BindSqlSelect(where = "channelId=${channelId}")
    List<Article> selectByChannelUd(long channelId);
}
