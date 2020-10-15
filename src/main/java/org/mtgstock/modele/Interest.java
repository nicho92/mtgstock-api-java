package org.mtgstock.modele;

import java.util.Date;

import org.mtgstock.tools.MTGStockConstants.CATEGORY;

public class Interest {
	
	private Boolean foil;
	private Date date;
	private Double percentage;
	private String interestType;
	private Double pricePresent;
	private Double pricePast;
	private Print print;
	private CATEGORY category;
	
	
	public Interest() {
		print = new Print();
	}
	
	@Override
	public String toString() {
		return print + ":" + getPricePresent();
	}
	
	public CATEGORY getCategory() {
		return category;
	}
	public void setCategory(CATEGORY category) {
		this.category = category;
	}
	public Boolean getFoil() {
		return foil;
	}
	public void setFoil(Boolean foil) {
		this.foil = foil;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public String getInterestType() {
		return interestType;
	}
	public void setInterestType(String interestType) {
		this.interestType = interestType;
	}
	public Double getPricePresent() {
		return pricePresent;
	}
	public void setPricePresent(Double pricePresent) {
		this.pricePresent = pricePresent;
	}
	public Double getPricePast() {
		return pricePast;
	}
	public void setPricePast(Double pricePast) {
		this.pricePast = pricePast;
	}
	public Print getPrint() {
		return print;
	}
	public void setPrint(Print print) {
		this.print = print;
	}
	
	
	
}
