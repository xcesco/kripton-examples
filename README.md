# kripton-examples
Some examples showing how to use kripton persistence library.

## Kripton with Content Providers Sample
This sample demonstrates how to expose data stored in the Kripton persistence library with Android's Content Provider framework.

This is a porting of the Room Persistence Content Provider Sample.

[https://github.com/xcesco/kripton-examples/tree/master/PersistenceContentProviderSample](https://github.com/xcesco/kripton-examples/tree/master/PersistenceContentProviderSample)

## Kripton Migration Sample
This is a Kripton version of [PersistenceMigrationsSample](https://raw.githubusercontent.com/googlesamples/android-architecture-components/master/PersistenceMigrationsSample/) by Google.

[https://github.com/xcesco/kripton-examples/tree/master/PersistenceMigrationsSample](https://github.com/xcesco/kripton-examples/tree/master/PersistenceMigrationsSample)

## Kripton Benchmarks
How Kripton is fast to parse and convert JSON data format? 

<img src="https://github.com/xcesco/wikis/raw/master/kripton/Nexus10_parse_1.png" />

[https://github.com/xcesco/kripton-examples/tree/master/kripton-benchmark](https://github.com/xcesco/kripton-examples/tree/master/kripton-benchmark)

For more information you read wiki page [Benchmarks](https://github.com/xcesco/kripton/wiki/Benchmarks)

## Rss Reader
This simple RRS Reader is a sample app that takes an BBC's RSS feed channel and try to download it locally and persists with Kripton into a SQLite database.

<img src="https://github.com/xcesco/kripton-examples/blob/master/rss-reader/screenshoots/screen1.png" width="300px"/>

```
http://feeds.bbci.co.uk/news/rss.xml?edition=int
```

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

## Shared Preferences with Live Data
This example show how Kripton allows managing Shared Preferences with Live Data.

[shared-preference-livedata](https://github.com/xcesco/kripton-examples/tree/master/shared-preference-livedata)

For more information read my article on medium [Kripton Library vs. Shared Preferences](https://medium.com/@xcesco/kripton-library-vs-shared-preferences-4365ffbf005a)
