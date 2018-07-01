package com.abubusoft.rssreader.service.model

import com.abubusoft.kripton.annotation.BindType
import com.abubusoft.kripton.annotation.BindXml
import com.abubusoft.kripton.xml.XmlType
import java.net.URL

@BindType
class Thumbnail {
    @BindXml(xmlType = XmlType.ATTRIBUTE)
    var width: Long = 0

    @BindXml(xmlType = XmlType.ATTRIBUTE)
    var height: Long = 0

    @BindXml(xmlType = XmlType.ATTRIBUTE)
    var url: URL? = null
}
