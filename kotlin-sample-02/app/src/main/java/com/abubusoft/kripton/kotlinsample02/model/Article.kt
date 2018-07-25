package com.abubusoft.kripton.kotlinsample02.model

import com.abubusoft.kripton.annotation.BindType


@BindType
data class Article(
        val id: Long,

        val guid: String,
        val title: String?,
        val description: String?,
        val read: Boolean
)