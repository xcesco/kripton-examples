package com.abubusoft.kripton.example.rssreader.service.model

import com.abubusoft.kripton.annotation.BindType
import com.abubusoft.kripton.annotation.BindXml
import com.abubusoft.kripton.xml.XmlType
import java.net.URL

@BindType
class Thumbnail(
    @BindXml(xmlType = XmlType.ATTRIBUTE)
    val width: Long,

    @BindXml(xmlType = XmlType.ATTRIBUTE)
    val height: Long,

    @BindXml(xmlType = XmlType.ATTRIBUTE)
    val url: URL?
)
