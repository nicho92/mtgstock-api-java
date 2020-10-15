# mtgstock-api-java
Java api for MTGStocks

Download link : 
	https://github.com/nicho92/mtgstock-api-java/tree/master/dist
	
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
		
		
