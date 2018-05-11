package com.abubusoft.kripton.examples.rssreader.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.abubusoft.kripton.examples.rssreader.service.model.Article;
import com.abubusoft.kripton.examples.rssreader.service.model.Channel;
import com.abubusoft.kripton.examples.rssreader.service.model.RssFeed;
import com.abubusoft.kripton.examples.rssreader.service.repository.RssRepository;

import java.util.List;

public class RssViewModel extends ViewModel {

    private RssRepository repository=RssRepository.getInstance();

    private LiveData<RssFeed> rssFeed;

    public LiveData<Channel> getChannel() {
        return repository.getChannel();
    }

    public LiveData<List<Article>> getArticles() {
        return repository.getArticleList();
    }

    public void markArticleAsRead(Article article) {
        repository.markArticleAsRead(article);
    }

    public void checkArticles() {
        repository.checkArticles();
    }
}
