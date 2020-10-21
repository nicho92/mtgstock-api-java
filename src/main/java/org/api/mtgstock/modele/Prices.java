package org.api.mtgstock.modele;

import java.util.EnumMap;
import java.util.Map;

import org.api.mtgstock.tools.MTGStockConstants.PRICES;

public class Prices {

	
	private Map<PRICES, PriceVariations> mapPrices;
	
	public Prices() {
		mapPrices = new EnumMap<>(PRICES.class);
	}
	
	
	public PriceVariations getPricesVariationsFor(PRICES p) {
		return mapPrices.get(p);
	}
	
	
	public void put(PRICES p, PriceVariations hp)
	{
		mapPrices.put(p, hp);
	}
	
	@Override
	public String toString() {
		
		StringBuilder temp = new StringBuilder();
		
		mapPrices.entrySet().forEach(e->temp.append(e.getKey()).append(":").append(e.getValue()).append("|"));
		
		return temp.toString();
		
	}
	
	
}
