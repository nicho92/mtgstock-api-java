package org.api.mtgstock.modele;

import java.util.Date;

import org.api.mtgstock.tools.MTGStockConstants.PRICES;

public class LowHighValues {

	
	private PRICES type;
	private Print print;
	private EntryValue<Date, Double> price;
	
	
	public PRICES getType() {
		return type;
	}
	public void setType(PRICES type) {
		this.type = type;
	}
	public Print getPrint() {
		return print;
	}
	public void setPrint(Print print) {
		this.print = print;
	}
	public EntryValue<Date, Double> getPrice() {
		return price;
	}
	public void setPrice(EntryValue<Date, Double> price) {
		this.price = price;
	}
	
	
	
}
