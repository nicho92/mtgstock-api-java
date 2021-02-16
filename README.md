# mtgstock-api-java
Java api for MTGStocks

	
Import via maven : 

		<dependency>
		  <groupId>com.github.nicho92</groupId>
		  <artifactId>mtgstock-api-java</artifactId>
		  <version>0.1.9</version>
		</dependency>


Import via Gradle : 

	implementation 'com.github.nicho92:mtgstock-api-java:0.1.7'



	
	
Usage : 

```java
			CardsService cardsService = new CardsService();
						 cardsService.search("Liliana of the veil");
			
			
			PriceService pService = new PriceService();
				         pService.getPricesFor(55799);
			
			
			InterestsService iService = new InterestsService();
							 iService.getMarketFoil();
							 iService.getAverage();
			
```		
		
		
