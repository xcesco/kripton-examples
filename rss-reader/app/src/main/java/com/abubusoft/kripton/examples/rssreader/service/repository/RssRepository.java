package com.abubusoft.kripton.examples.rssreader.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.examples.rssreader.service.model.Channel;
import com.abubusoft.kripton.examples.rssreader.service.persistence.BindRssDataSource;
import com.abubusoft.kripton.examples.rssreader.service.api.RssService;
import com.abubusoft.kripton.examples.rssreader.service.model.Article;
import com.abubusoft.kripton.examples.rssreader.service.model.RssFeed;
import com.abubusoft.kripton.examples.rssreader.service.persistence.DaoArticleImpl;
import com.abubusoft.kripton.examples.rssreader.service.persistence.DaoChannelImpl;
import com.abubusoft.kripton.examples.rssreader.service.persistence.DaoRssImpl;
import com.abubusoft.kripton.examples.rssreader.service.persistence.FilterType;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RssRepository {
    private BindRssDataSource dataSource = BindRssDataSource.getInstance();

    private RssService rssService = new RssService();

    /**
     * retrieves articles from rest services
     */
    public void downloadArticles() {
        rssService.execute().enqueue(new Callback<RssFeed>() {

            @Override
            public void onResponse(Call<RssFeed> call, Response<RssFeed> response) {
                RssFeed rssFeed = response.body();
                BindRssDataSource.getInstance().execute(daoFactory -> {
                    RssFeed currentRss = null;
                    //TRICK: for the moment we retrieve only from bbc, uid will be *always* bbc
                    DaoRssImpl daoRss = daoFactory.getDaoRss();
                    DaoChannelImpl daoChannels = daoFactory.getDaoChannel();
                    DaoArticleImpl daoArticles = daoFactory.getDaoArticle();

                    // select or add rss
                    currentRss = daoRss.selectOne(RssService.RSS_NAME);
                    if (currentRss == null) {
                        currentRss = new RssFeed();
                        currentRss.version = rssFeed.version;
                        currentRss.uid = RssService.RSS_NAME;
                        daoRss.insert(currentRss);
                    }
                    for (Channel channelItem : rssFeed.channels) {
                        // select or add channel
                        Channel currentChannel = daoChannels.selectOneByRssFeedId(currentRss.id);
                        if (currentChannel == null) {
                            channelItem.rssFeedId = currentRss.id;
                            daoChannels.insert(channelItem);
                            currentChannel = channelItem;
                        }

                        for (Article article : channelItem.articles) {
                            // select or add article
                            Article currentArticle = daoArticles.selectByGuid(currentChannel.id, article.guid);
                            if (currentArticle == null) {
                                article.channelId = currentChannel.id;
                                daoArticles.insert(article);
                            }

                        }
                    }

                    return TransactionResult.COMMIT;
                });


            }

            @Override
            public void onFailure(Call<RssFeed> call, Throwable throwable) {

            }

            // Error handling will be explained in the next article …
        });

    }

    public MutableLiveData<List<Article>> getArticleList(FilterType filter) {
        return this.dataSource.getDaoArticle().selectByChannel(filter.getSql());
    }

    private static RssRepository instance;

    public static RssRepository getInstance() {
        if (instance == null) {
            instance = new RssRepository();
        }

        return instance;
    }

    /**
     * @param article
     */
    public void markArticleAsRead(Article article) {
        this.dataSource.executeBatch(daoFactory -> {
            daoFactory.getDaoArticle().update(article.id, article.channelId, true);
            return null;
        });
    }


    public LiveData<Channel> getChannel() {
        return this.dataSource.getDaoChannel().selectOne();
    }

    public List<Article> updateFilter(FilterType filter) {
        return this.dataSource.executeBatch(daoFactory -> daoFactory.getDaoArticle().selectByChannelForLiveData(filter.getSql()));
    }
}
