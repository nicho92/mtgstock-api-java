package org.mtgstock.modele;

import java.util.ArrayList;
import java.util.List;

import org.mtgstock.tools.MTGStockConstants.PRICES;
import org.mtgstock.tools.MTGStockConstants.RARITY;

public class PriceHash {

	private RARITY rarity;
	
	private List<EntryValue<PRICES, Double>> avg;
	private List<EntryValue<PRICES, Double>> sum;
	
	public PriceHash() {
		avg = new ArrayList<>();
		sum = new ArrayList<>();
	}

	
	public List<EntryValue<PRICES, Double>> getSum() {
		return sum;
	}


	public void setSum(List<EntryValue<PRICES, Double>> sum) {
		this.sum = sum;
	}


	public RARITY getRarity() {
		return rarity;
	}
	public void setRarity(RARITY rarity) {
		this.rarity = rarity;
	}
	public List<EntryValue<PRICES, Double>> getAvg() {
		return avg;
	}
	public void setAvg(List<EntryValue<PRICES, Double>> avg) {
		this.avg = avg;
	}
	
	
	
	
	
}
