package com.abubusoft.kripton.example.rssreader.service.model

import com.abubusoft.kripton.annotation.BindType


@BindType
data class Image(
        var url: String? = null,
        var title: String? = null,
        var link: String? = null,
        var width: Int = 88,
        var height: Int = 31
)