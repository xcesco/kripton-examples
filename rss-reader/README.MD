## Rss Reader ##
This simple RRS Reader is a sample app that takes an BBC's RSS feed channel

```
http://feeds.bbci.co.uk/news/rss.xml?edition=int
```

and try to download it locally and persists with Kripton into a SQLite database.

### Why? ###
This simple app was built as showroom for some features of Kripton:



To copy database from emulator to workstation just use:

```
adb shell "run-as com.abubusoft.kripton.examples.rssreader cat /data/user/0/com.abubusoft.kripton.examples.rssreader/databases/rss.db" > data.db
```
