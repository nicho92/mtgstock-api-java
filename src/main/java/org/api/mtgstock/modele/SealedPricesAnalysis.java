package org.api.mtgstock.modele;

import java.util.EnumMap;
import java.util.Map;

import org.api.mtgstock.tools.MTGStockConstants.PRICES;

public class SealedPricesAnalysis {

	
	private Map<PRICES,PriceVariations> prices;
	
		
	public SealedPricesAnalysis() {
		prices = new EnumMap<>(PRICES.class);
	}

	
	public Map<PRICES, PriceVariations> getPrices() {
		return prices;
	}
	
		
}
