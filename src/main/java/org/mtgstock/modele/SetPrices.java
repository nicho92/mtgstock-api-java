package org.mtgstock.modele;

import java.util.EnumMap;
import java.util.Map;

import org.mtgstock.tools.MTGStockConstants.PRICES;

public class SetPrices {

	private Set set;
	private Map<PRICES, Double> prices;
	private Integer num;
	
	@Override
	public String toString() {
		return String.valueOf(getSet());
	}
	
	public SetPrices() {
		prices = new EnumMap<>(PRICES.class);
	}

	public void put(PRICES p, Double value)
	{
		prices.put(p, value);
	}
	
	

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Set getSet() {
		return set;
	}


	public void setSet(Set set) {
		this.set = set;
	}


	public Map<PRICES, Double> getPrices() {
		return prices;
	}


	public void setPrices(Map<PRICES, Double> prices) {
		this.prices = prices;
	}
	
	
}
