package org.mtgstock.modele;

import java.util.Date;
import java.util.List;

import org.mtgstock.tools.MTGStockConstants;

public class Interests {

	private Date date;
	
	private List<Interest> average;
	private List<Interest> averageFoil;
	private List<Interest> market;
	private List<Interest> marketFoil;
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	public List<Interest> getAverage() {
		return average;
	}
	public void setAverage(List<Interest> average) {
		this.average = average;
	}
	public List<Interest> getAverageFoil() {
		return averageFoil;
	}
	public void setAverageFoil(List<Interest> averageFoil) {
		this.averageFoil = averageFoil;
	}
	public List<Interest> getMarket() {
		return market;
	}
	public void setMarket(List<Interest> market) {
		this.market = market;
	}
	public List<Interest> getMarketFoil() {
		return marketFoil;
	}
	public void setMarketFoil(List<Interest> marketFoil) {
		this.marketFoil = marketFoil;
	}
	
	
	
}
