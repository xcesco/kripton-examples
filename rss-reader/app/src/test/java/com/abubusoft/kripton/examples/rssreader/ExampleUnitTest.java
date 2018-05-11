package com.abubusoft.kripton.examples.rssreader;

import com.abubusoft.kripton.examples.rssreader.service.api.RssService;
import com.abubusoft.kripton.examples.rssreader.service.model.Channel;
import com.abubusoft.kripton.examples.rssreader.service.model.RssFeed;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws IOException {
        RssService controller=new RssService();
        RssFeed result = controller.execute().execute().body();

        for (Channel channel:result.channels) {
            System.out.println(channel.description);
        }

        assertEquals(4, 2 + 2);
    }
}