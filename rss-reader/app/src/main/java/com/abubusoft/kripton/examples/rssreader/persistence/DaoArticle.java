package com.abubusoft.kripton.examples.rssreader.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.examples.rssreader.model.Article;

@BindDao(Article.class)
public interface DaoArticle extends DaoBase<Article> {
}
