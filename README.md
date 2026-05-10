# mtgstock-api-java

[![Maven Central](https://img.shields.io/maven-central/v/com.github.nicho92/mtgstock-api-java.svg)](https://central.sonatype.com/artifact/com.github.nicho92/mtgstock-api-java)
[![Java](https://img.shields.io/badge/Java-21%2B-blue.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-Apache%202.0-green.svg)](http://www.apache.org/licenses/LICENSE-2.0)


Java client library for the public MTGStocks endpoints. It provides small service classes for searching Magic: The Gathering cards, browsing sets and sealed products, reading price histories, following interests, analytics, metagame data, decks, tournaments, archetypes, and MTGStocks news.

## Table of contents

- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
  - [Maven](#maven)
  - [Gradle](#gradle)
- [Quick start](#quick-start)
- [Available services](#available-services)
  - [CardsService](#cardsservice)
  - [PriceService](#priceservice)
  - [InterestsService](#interestsservice)
  - [AnalyticsService](#analyticsservice)
  - [DecksServices](#decksservices)
  - [NewsService](#newsservice)
- [Request monitoring](#request-monitoring)
- [Model packages](#model-packages)
- [Build from source](#build-from-source)
- [Notes](#notes)
- [License](#license)

## Features

- Card search and print lookup.
- Set and sealed product browsing.
- Historical prices for low, average, high, foil, market, and market foil price categories.
- Expected value analysis for sets and sealed products.
- MTGStocks interests for average and market movers, including foil variants.
- Analytics for handy lists, all-time lows/highs, most-played cards, and metagames.
- Tournament, archetype, deck list, and deck detail helpers.
- News listing from MTGStocks.
- Optional request listener hook for logging, metrics, or debugging outgoing API calls.

## Requirements

- Java 13 or newer.
- Maven 3.x for local builds.
- Network access to `https://api.mtgstocks.com` at runtime.

## Installation

The artifact coordinates are:

- Group ID: `com.github.nicho92`
- Artifact ID: `mtgstock-api-java`
- Current project version: `1.1.7`

### Maven

```xml
<dependency>
  <groupId>com.github.nicho92</groupId>
  <artifactId>mtgstock-api-java</artifactId>
  <version>1.1.7</version>
</dependency>
```

### Gradle

```gradle
implementation 'com.github.nicho92:mtgstock-api-java:1.1.7'
```

## Quick start

```java
import java.util.List;

import org.api.mtgstock.modele.FullPrint;
import org.api.mtgstock.modele.Interest;
import org.api.mtgstock.modele.Prices;
import org.api.mtgstock.modele.SearchResult;
import org.api.mtgstock.services.CardsService;
import org.api.mtgstock.services.InterestsService;
import org.api.mtgstock.services.PriceService;

public class Example {
  public static void main(String[] args) throws Exception {
    CardsService cardsService = new CardsService();
    PriceService priceService = new PriceService();
    InterestsService interestsService = new InterestsService();

    SearchResult bestMatch = cardsService.getBestResult("Liliana of the Veil");
    FullPrint print = cardsService.getCard(bestMatch);
    Prices prices = priceService.getPricesFor(print);

    List<Interest> marketMovers = interestsService.getInterests().getMarket();

    System.out.println(print.getName());
    System.out.println(prices);
    System.out.println("Market movers: " + marketMovers.size());
  }
}
```

## Available services

### CardsService

Use `CardsService` for card and product discovery.

```java
CardsService cardsService = new CardsService();

List<SearchResult> results = cardsService.search("Sol Ring");
SearchResult bestResult = cardsService.getBestResult("Sol Ring");
FullPrint card = cardsService.getCard(bestResult);

List<CardSet> sets = cardsService.listSets();
CardSet set = cardsService.getSetByCode("ltr");
List<Print> prints = cardsService.getPrintsBySet(set);
List<SealedProduct> sealedProducts = cardsService.getSealedProduct(set);
```

Common methods include:

- `search(String name)`
- `getBestResult(String name)`
- `getCard(SearchResult result)`, `getCard(Print print)`, and `getCard(Integer id)`
- `toPrints(List<SearchResult> results)` and `toPrints(Integer... ids)`
- `listSets()`
- `getSetByName(String name)`, `getSetByCode(String code)`, and `getSetById(int id)`
- `getPrintsBySet(CardSet set)` and `getPrintsBySetCode(String code)`
- `getSealedProduct(CardSet set)` and `getSealedProduct(Integer setId)`

### PriceService

Use `PriceService` for print, set, and sealed-product pricing.

```java
PriceService priceService = new PriceService();

Prices allPrices = priceService.getPricesFor(print);
PriceVariations marketHistory = priceService.getPricesFor(print.getId(), PRICES.MARKET);
SetPricesAnalysis setAnalysis = priceService.getSetPricesAnalysis(set);
SealedPricesAnalysis sealedAnalysis = priceService.getSealedPrices(sealedProduct);
```

Supported price categories are exposed by `MTGStockConstants.PRICES`:

- `LOW`
- `AVG`
- `HIGH`
- `FOIL`
- `MARKET`
- `MARKET_FOIL`

### InterestsService

Use `InterestsService` to retrieve MTGStocks interests and movers.

```java
InterestsService interestsService = new InterestsService();

Interests interests = interestsService.getInterests();
List<Interest> market = interests.getMarket();
List<Interest> marketFoil = interests.getMarketFoil();
List<Interest> average = interests.getAverage();
List<Interest> averageFoil = interests.getAverageFoil();

List<Interest> commanderMarket = interestsService.getInterestFor(INTEREST.MARKET, false, FORMAT.COMMANDER);
```

### AnalyticsService

Use `AnalyticsService` for handy lists, expected values, all-time values, play stats, and metagames.

```java
AnalyticsService analyticsService = new AnalyticsService();

List<HandyList> handyLists = analyticsService.listHandyList();
List<LowHighValues> allTimes = analyticsService.listAllTimes();
List<SetPrices> expectedValues = analyticsService.getExpectedValues();
List<Played> modernMostPlayed = analyticsService.getMostPlayedCard(FORMAT.MODERN);
List<Metagame> modernMetagame = analyticsService.getMetagamesFor(FORMAT.MODERN);
```

### DecksServices

Use `DecksServices` for tournament, archetype, and deck data.

```java
DecksServices decksServices = new DecksServices();

List<Tournament> tournaments = decksServices.listTournaments(FORMAT.MODERN);
List<Archetype> archetypes = decksServices.listArchetypes(FORMAT.MODERN);
List<DeckInfo> deckInfos = decksServices.listDeckForTournament(tournaments.get(0));
Deck deck = decksServices.getDecksDetails(deckInfos.get(0));
```

### NewsService

Use `NewsService` to list MTGStocks news entries.

```java
NewsService newsService = new NewsService();
List<News> news = newsService.listNews();
```

## Request monitoring

Every service extends `AbstractMTGStockService`, which exposes `setListener(URLCallListener listener)`. Register a listener when you need to capture request URLs, durations, requests, or responses.

```java
CardsService cardsService = new CardsService();

cardsService.setListener(callInfo -> {
  System.out.println(callInfo.getUrl());
  System.out.println(callInfo.getDuration() + " ms");
});

cardsService.search("Lightning Bolt");
```

## Model packages

Primary value objects are under `org.api.mtgstock.modele`, including:

- Card and print models: `SearchResult`, `Print`, `FullPrint`, `CardDetails`, `CardSet`, `Legality`.
- Price models: `Prices`, `PriceVariations`, `SetPrices`, `SetPricesAnalysis`, `SealedPricesAnalysis`, `PriceHash`, `EntryValue`.
- Analytics and play models: `HandyList`, `LowHighValues`, `Played`, `Metagame`.
- Deck models: `Tournament`, `Archetype`, `DeckInfo`, `Deck`, `DeckCard`.
- Other models: `Interest`, `Interests`, `SealedProduct`, `News`, `URLCallInfo`.

## Build from source

Clone the repository and build the jar with Maven:

```bash
git clone https://github.com/nicho92/mtgstock-api-java.git
cd mtgstock-api-java
mvn clean package
```

Run a compile-only verification:

```bash
mvn -DskipTests compile
```

## Notes

- This project wraps MTGStocks public endpoints and depends on MTGStocks response shapes remaining compatible.
- Service methods perform network calls and may throw `IOException` for request or parsing failures.
- Some service methods catch exceptions internally and return an empty object or list while logging the error.
- The package name uses `modele` for model classes.

## License

This project is licensed under the Apache License, Version 2.0.
