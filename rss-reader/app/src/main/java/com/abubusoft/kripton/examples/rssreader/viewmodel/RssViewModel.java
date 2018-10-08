package com.abubusoft.kripton.examples.rssreader.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.examples.rssreader.service.model.Article;
import com.abubusoft.kripton.examples.rssreader.service.model.Channel;
import com.abubusoft.kripton.examples.rssreader.service.model.RssFeed;
import com.abubusoft.kripton.examples.rssreader.service.persistence.FilterType;
import com.abubusoft.kripton.examples.rssreader.service.repository.RssRepository;

import java.util.List;

public class RssViewModel extends ViewModel {

    private final LiveData<List<Article>> articles;

    private final MutableLiveData<FilterType> filterLiveData = new MutableLiveData<FilterType>();

    public RssViewModel() {
        articles=Transformations.switchMap(filterLiveData, v -> repository.getArticleList(v));
    }

    private RssRepository repository = RssRepository.getInstance();

    public LiveData<Channel> getChannel() {
        return repository.getChannel();
    }

    public LiveData<List<Article>> getArticles() {
        return articles;
    }

    public void markArticleAsRead(Article article) {
        if (article.read==false) {
            repository.markArticleAsRead(article);
        } else {
            Logger.info("Article already read");
        }
    }

    public void downloadArticles() {
        repository.downloadArticles();
    }

    public void updateFilter(FilterType filter) {
        filterLiveData.setValue(filter);
    }

}
