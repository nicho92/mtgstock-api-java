package org.api.mtgstock.modele;

import java.util.Date;

import org.api.mtgstock.tools.MTGStockConstants.FORMAT;
import org.api.mtgstock.tools.MTGStockConstants.INTEREST;
import org.api.mtgstock.tools.MTGStockConstants.PRICES;

public class Interest {
	
	private Boolean foil;
	private Date date;
	private Double percentage;
	private String interestType;
	private Double pricePresent;
	private Double pricePast;
	private Print print;
	private INTEREST category;
	
	
	public Interest() {
		print = new Print();
	}
	
	
	public boolean isLegalFor(FORMAT f)
	{
		return print.isLegalFor(f);
	}
	
	public double getPriceDayChange()
	{
		return getPricePresent() - getPricePast();
	}

	
	@Override
	public String toString() {
		return print + ":" + getPricePresent();
	}
	
	public INTEREST getCategory() {
		return category;
	}
	public void setCategory(INTEREST category) {
		this.category = category;
	}
	public Boolean isFoil() {
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
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj==null)
			return false;
		
		if(!(obj instanceof Interest))
			return false;

		
		return this.getPrint().getId() == ((Interest)obj).getPrint().getId();
	}
	
}
