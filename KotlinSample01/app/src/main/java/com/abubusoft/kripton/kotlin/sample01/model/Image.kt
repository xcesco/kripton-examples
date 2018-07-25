package com.abubusoft.kripton.kotlin.sample01.model

import com.abubusoft.kripton.annotation.BindType


@BindType
data class Image(
        val url: String? = null,
        val title: String? = null,
        val link: String? = null,
        val width: Int = 88,
        val height: Int = 31
)