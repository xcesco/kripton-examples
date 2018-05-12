package com.abubusoft.kripton.examples.rssreader.service.persistence;

import android.arch.lifecycle.LiveData;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.examples.rssreader.service.model.Article;

import java.util.List;

@BindDao(Article.class)
public interface DaoArticle extends DaoBase<Article> {

    @BindSqlUpdate(where = "id=${id}")
    void update(long id, long channelId, boolean read);

    @BindSqlSelect
    LiveData<List<Article>> selectByChannel();

    @BindSqlSelect
    LiveData<List<Article>> selectByChannel(@BindSqlDynamicWhere String where);

    @BindSqlSelect(where = "channelId=${channelId}")
    List<Article> selectByChannelUd(long channelId);

    @BindSqlSelect(where="channelId=${channelId} AND guid=${guid}")
    Article selectByGuid(long channelId, String guid);
}
