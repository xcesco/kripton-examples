## Rss Reader ##
This simple RRS Reader is a sample app that takes an BBC's RSS feed channel

<img src="https://github.com/xcesco/kripton-examples/blob/master/rss-reader/screenshoots/screen1.png" width="300px"/>

```
http://feeds.bbci.co.uk/news/rss.xml?edition=int
```

and try to download it locally and persists with Kripton into a SQLite database.

### Why? ###
This simple app was built as showroom for some features of Kripton:
- Integration with Retrofit to consume REST service
- Convert XML into Java beans
- Persist data on SQLite database
- How to embed an entity in a field and how to use it as sqlite table
- How to use a unique model rapresentations for REST services and SQLite persistence
- SQLite type adapter usage
- SQLite relationship definition
- How generate Live Data from DAO queries
- How to write queries in compact mode
- How to use dynamic where conditions in SQL queries

## How to copy locally the database ##
To copy database from emulator to workstation just use:

### Anything before API 24 ###
```
adb pull /data/data/com.abubusoft.kripton.examples.rssreader/databases/rss.db
```

### API 24 and later ###
```
adb exec-out run-as com.abubusoft.kripton.examples.rssreader cat ./databases/rss.db > rss.db
```

