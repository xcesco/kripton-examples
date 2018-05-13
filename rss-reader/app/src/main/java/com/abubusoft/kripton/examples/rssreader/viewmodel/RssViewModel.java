package com.abubusoft.kripton.examples.rssreader.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.examples.rssreader.service.model.Article;
import com.abubusoft.kripton.examples.rssreader.service.model.Channel;
import com.abubusoft.kripton.examples.rssreader.service.model.RssFeed;
import com.abubusoft.kripton.examples.rssreader.service.persistence.FilterType;
import com.abubusoft.kripton.examples.rssreader.service.repository.RssRepository;

import java.util.List;

public class RssViewModel extends ViewModel {

    private MutableLiveData<List<Article>> articles = null;

    private FilterType filter = FilterType.ALL;

    private RssRepository repository = RssRepository.getInstance();

    public LiveData<Channel> getChannel() {
        return repository.getChannel();
    }

    public LiveData<List<Article>> getArticles() {
        if (articles == null) {
            articles = repository.getArticleList();
        }

        return articles;
    }

    public void markArticleAsRead(Article article) {
        repository.markArticleAsRead(article);
    }

    public void downloadArticles() {
        repository.downloadArticles();

       // loadArticles(filter);
    }

    public void updateFilter(FilterType filter) {
        repository.updateFilter(filter);
    }
}
