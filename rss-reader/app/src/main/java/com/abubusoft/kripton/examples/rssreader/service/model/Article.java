package com.abubusoft.kripton.examples.rssreader.service.model;

import java.net.URL;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.annotation.BindXmlNamespace;
import com.abubusoft.kripton.annotation.BindXmlType;

@BindType("item")
@BindXmlType(namespaces={
		@BindXmlNamespace(prefix="dc",uri="http://purl.org/dc/elements/1.1/"),
		@BindXmlNamespace(prefix="content",uri="http://purl.org/dc/elements/1.1/"),
})
@BindSqlType(name = "articles")
public class Article extends Entity {
	public String title;
	public String description;
	public URL link;
	public String author;

	@BindSqlColumn(nullable = false, columnType = ColumnType.UNIQUE)
	public String guid;

	public URL comments;

	@BindSqlColumn(parentEntity = Channel.class)
	public long channelId;

	@Bind("thumbnail")
	@BindXml(namespace="media")
	public Thumbnail thumbnail;

	public boolean read;
}
