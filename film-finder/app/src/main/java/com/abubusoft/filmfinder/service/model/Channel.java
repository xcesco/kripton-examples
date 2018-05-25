package com.abubusoft.kripton.examples.rssreader.service.model;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlRelation;
import com.abubusoft.kripton.android.annotation.BindSqlType;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindSqlType(name="channels")
public class Channel extends Entity {

	public String title;

	@BindSqlColumn(columnType = ColumnType.UNIQUE)
	public String link;

	public String description;
	public String language;
	public String copyright;
	
	@BindAdapter(adapter = DateAdapter.class)
	public Date pubDate;
	
	@BindAdapter(adapter = DateAdapter.class)
	public Date lastBuildDate;
	
	public Image image;

	@BindSqlColumn(parentEntity = RssFeed.class)
	public long rssFeedId;

	@Bind("item")
	@BindSqlRelation(foreignKey = "channelId")
	public List<Article> articles;
}
