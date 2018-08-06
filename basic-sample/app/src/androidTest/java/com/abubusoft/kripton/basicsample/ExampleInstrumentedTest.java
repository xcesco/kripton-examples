package com.abubusoft.kripton.basicsample;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.basicsample.db.AppDataSourcePopulator;
import com.abubusoft.kripton.basicsample.db.BindAppDataSource;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws InterruptedException {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        KriptonLibrary.init(appContext);

        DataSourceOptions options = DataSourceOptions.builder().inMemory(true).populator(new AppDataSourcePopulator()).build();

        BindAppDataSource.build(options);

        KriptonLibrary.getExecutorService().awaitTermination(10, TimeUnit.SECONDS);

        assertEquals("com.abubusoft.kripton.basicsample", appContext.getPackageName());
    }
}
