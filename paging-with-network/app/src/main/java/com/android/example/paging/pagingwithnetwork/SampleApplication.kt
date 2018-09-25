package com.android.example.paging.pagingwithnetwork

import android.app.Application
import com.abubusoft.kripton.android.KriptonLibrary

class SampleApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        KriptonLibrary.init(this);
    }
}