package com.abubusoft.kripton.example.rssreader.service.model

import com.abubusoft.kripton.annotation.BindType
import com.abubusoft.kripton.annotation.BindXml
import com.abubusoft.kripton.xml.XmlType
import java.net.URL

@BindType
class Thumbnail(
        @BindXml(xmlType = XmlType.ATTRIBUTE)
        val width: Long = 0,

        @BindXml(xmlType = XmlType.ATTRIBUTE)
        val height: Long = 0,

        @BindXml(xmlType = XmlType.ATTRIBUTE)
        val url: URL? = null
)
