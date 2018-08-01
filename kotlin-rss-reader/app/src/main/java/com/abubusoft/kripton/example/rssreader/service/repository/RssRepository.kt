package com.abubusoft.kripton.example.rssreader.service.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.abubusoft.kripton.android.sqlite.TransactionResult
import com.abubusoft.kripton.example.rssreader.service.api.RssService
import com.abubusoft.kripton.example.rssreader.service.model.Article
import com.abubusoft.kripton.example.rssreader.service.model.Channel
import com.abubusoft.kripton.example.rssreader.service.model.RssFeed
import com.abubusoft.kripton.example.rssreader.service.persistence.BindRssDataSource
import com.abubusoft.kripton.example.rssreader.service.persistence.FilterType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RssRepository {
    private val dataSource = BindRssDataSource.getInstance()

    private val rssService = RssService()

    val channel: LiveData<Channel>
        get() = this.dataSource.daoChannel.selectOne()

    fun downloadArticles() =
            rssService.execute().enqueue(object : Callback<RssFeed> {

                override fun onResponse(call: Call<RssFeed>, response: Response<RssFeed>) {
                    val rssFeed = response.body()

                    BindRssDataSource.getInstance().execute { daoFactory ->
                        var currentRss: RssFeed?
                        //TRICK: for the moment we retrieve only from bbc, uid will be *always* bbc
                        val daoRss = daoFactory.daoRss
                        val daoChannels = daoFactory.daoChannel
                        val daoArticles = daoFactory.daoArticle

                        // select or add rss
                        currentRss = daoRss.selectOne(RssService.RSS_NAME)
                        if (currentRss == null) {
                            currentRss = RssFeed()
                            currentRss.version = rssFeed!!.version
                            currentRss.uid = RssService.RSS_NAME
                            currentRss=daoRss.insert(currentRss)
                        }
                        for (channelItem in rssFeed?.channels!!) {
                            // select or add channel
                            var currentChannel = daoChannels.selectOneByRssFeedId(currentRss.id)
                            if (currentChannel == null) {
                                channelItem.rssFeedId = currentRss.id
                                currentChannel= daoChannels.insert(channelItem)
                            }

                            for (article in channelItem.articles!!) {
                                // select or add article
                                var currentArticle = daoArticles.selectByGuid(currentChannel.id, article.guid
                                        ?: "")
                                if (currentArticle == null) {
                                    article.channelId = currentChannel.id
                                    daoArticles.insert(article)
                                }

                            }
                        }

                        TransactionResult.COMMIT
                    }


                }

                override fun onFailure(call: Call<RssFeed>, throwable: Throwable) {
                    // Error handling will be explained in the next article …
                }

            })


    fun getArticleList(filter: FilterType) =
            this.dataSource.daoArticle.selectByChannel(filter.sql)


    /**
     * @param article
     */
    fun markArticleAsRead(article: Article) =
            this.dataSource.executeBatch { daoFactory ->
                daoFactory.daoArticle.update(article.id, article.channelId, true)
                null
            }


    private object Holder {
        val INSTANCE = RssRepository()
    }

    companion object {
        val instance: RssRepository by lazy { Holder.INSTANCE }
    }

}