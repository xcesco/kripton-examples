package com.abubusoft.kripton.examples.rssreader.model;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType
public class Thumbnail {
    @BindXml(xmlType = XmlType.ATTRIBUTE)
    public long width;

    @BindXml(xmlType = XmlType.ATTRIBUTE)
    public long height;

    @BindXml(xmlType = XmlType.ATTRIBUTE)
    public long url;
}
