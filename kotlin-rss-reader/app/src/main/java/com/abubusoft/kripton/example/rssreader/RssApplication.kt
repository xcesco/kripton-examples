package com.abubusoft.kripton.example.rssreader

import com.abubusoft.kripton.android.KriptonLibrary
import android.app.Application


class RssApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KriptonLibrary.init(this)
    }
}
