## Rss Reader ##
This simple RRS Reader is a sample app that takes an BBC's RSS feed channel

```
http://feeds.bbci.co.uk/news/rss.xml?edition=int
```

and try to download it locally and persists with Kripton into a SQLite database.

### Why? ###
This simple app was built as showroom for some features of Kripton:

## How to copy locally the database ##
To copy database from emulator to workstation just use:

### Anything before API 24
```
adb pull /data/data/com.abubusoft.kripton.examples.rssreader/databases/rss.db
```

### API 24 and later
```
adb exec-out run-as com.abubusoft.kripton.examples.rssreader cat ./databases/rss.db > rss.db
```

