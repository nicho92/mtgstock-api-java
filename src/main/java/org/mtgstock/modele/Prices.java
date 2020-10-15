package org.mtgstock.modele;

import java.util.EnumMap;
import java.util.Map;

import org.mtgstock.tools.MTGStockConstants.PRICES;

public class Prices {

	
	private Map<PRICES, PriceVariations> prices;
	
	public Prices() {
		prices = new EnumMap<>(PRICES.class);
	}
	
	
	public PriceVariations getPrices(PRICES p) {
		return prices.get(p);
	}
	
	
	public void put(PRICES p, PriceVariations hp)
	{
		prices.put(p, hp);
	}
	
	@Override
	public String toString() {
		
		StringBuilder temp = new StringBuilder();
		
		prices.entrySet().forEach(e->temp.append(e.getKey()).append(":").append(e.getValue()).append("|"));
		
		return temp.toString();
		
	}
	
	
}
