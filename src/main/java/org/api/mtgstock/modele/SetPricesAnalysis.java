package org.api.mtgstock.modele;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.api.mtgstock.tools.MTGStockConstants.PRICES;

public class SetPricesAnalysis {

	
	private Map<PRICES,PriceVariations> prices;
	private CardSet cardSet;
	private List<PriceHash> priceHash;
	private PriceHash booster;
	
	
	public PriceHash getBooster() {
		return booster;
	}


	public void setBooster(PriceHash booster) {
		this.booster = booster;
	}


	public List<PriceHash> getPriceHash() {
		return priceHash;
	}


	public void setPriceHash(List<PriceHash> priceHash) {
		this.priceHash = priceHash;
	}


	public SetPricesAnalysis() {
		prices = new EnumMap<>(PRICES.class);
		priceHash = new ArrayList<>();
	}


	public Map<PRICES, PriceVariations> getPrices() {
		return prices;
	}


	public void setPrices(Map<PRICES, PriceVariations> prices) {
		this.prices = prices;
	}


	public CardSet getCardSet() {
		return cardSet;
	}


	public void setCardSet(CardSet cardSet) {
		this.cardSet = cardSet;
	}
	
	
	
	
	
	
		
}
