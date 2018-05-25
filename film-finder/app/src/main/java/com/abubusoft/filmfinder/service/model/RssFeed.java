package com.abubusoft.kripton.examples.rssreader.service.model;

import java.util.List;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlRelation;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType(value="rss")
public class RssFeed extends Entity {

	@BindSqlColumn(columnType = ColumnType.UNIQUE)
	public String uid;

	@BindXml(xmlType = XmlType.ATTRIBUTE)
	public String version;

	@Bind("channel")
	@BindSqlRelation(foreignKey = "rssFeedId")
	public List<Channel> channels;
}