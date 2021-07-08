package org.api.mtgstock.modele;

import java.util.EnumMap;
import java.util.Map;

import org.api.mtgstock.tools.MTGStockConstants.PRICES;

public class SealedProduct {
	private int id;
	private String name;
	private String urlImage;
	private String slug;
	private Map<PRICES,Double> latestPrices;
	
	
	public SealedProduct() {
		latestPrices = new EnumMap<>(PRICES.class);
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public Map<PRICES, Double> getLatestPrices() {
		return latestPrices;
	}
	public void setLatestPrices(Map<PRICES, Double> latestPrices) {
		this.latestPrices = latestPrices;
	}
	
	
	
	
	
	
}
