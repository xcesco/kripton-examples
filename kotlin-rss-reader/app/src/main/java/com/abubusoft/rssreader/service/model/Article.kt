package com.abubusoft.rssreader.service.model

import com.abubusoft.kripton.android.annotation.BindSqlType
import com.abubusoft.kripton.annotation.BindType
import com.abubusoft.kripton.annotation.BindXmlNamespace
import com.abubusoft.kripton.annotation.BindXmlType
import java.net.URL

@BindType("item")
@BindXmlType(namespaces = arrayOf(BindXmlNamespace(prefix = "dc", uri = "http://purl.org/dc/elements/1.1/"), BindXmlNamespace(prefix = "content", uri = "http://purl.org/dc/elements/1.1/")))
@BindSqlType(name = "articles")
data class Article(var title: String? = null,
                   var description: String? = null,
                   var link: URL? = null,
                   var author: String? = null) : Entity(0) {

}