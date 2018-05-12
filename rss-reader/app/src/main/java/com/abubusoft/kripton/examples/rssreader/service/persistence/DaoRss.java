package com.abubusoft.kripton.examples.rssreader.service.persistence;

import android.arch.lifecycle.LiveData;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlChildSelect;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.examples.rssreader.service.model.RssFeed;

import java.util.List;

@BindDao(RssFeed.class)
public interface DaoRss extends DaoBase<RssFeed> {

    @BindSqlSelect(where="uid=${uid}")
    RssFeed selectOne(String uid);

}
