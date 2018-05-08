package com.abubusoft.kripton.examples.rssreader;

import com.abubusoft.kripton.examples.rssreader.api.RssController;
import com.abubusoft.kripton.examples.rssreader.model.Channel;
import com.abubusoft.kripton.examples.rssreader.model.RSSFeed;

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
        RssController controller=new RssController();
        RSSFeed result = controller.execute().execute().body();

        for (Channel channel:result.channels) {
            System.out.println(channel.description);
        }

        assertEquals(4, 2 + 2);
    }
}