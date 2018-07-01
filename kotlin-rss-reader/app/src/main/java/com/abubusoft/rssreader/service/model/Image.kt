package com.abubusoft.rssreader.service.model

import com.abubusoft.kripton.annotation.BindType


@BindType
class Image {
    var url: String? = null
    var title: String? = null
    var link: String? = null
    var width = 88
    var height = 31
}