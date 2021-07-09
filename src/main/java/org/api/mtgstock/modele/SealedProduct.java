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
	
	public boolean isVIP()
	{
		return getName().contains("VIP");
	}
	
	public boolean isBundle()
	{
		return getName().startsWith("Bundle");
	}
	
	public boolean isGift()
	{
		return getName().contains("Giflt");
	}
	
	public boolean isFatPack()
	{
		return getName().startsWith("Fat Pack");
	}
	
	public boolean isBooster()
	{
		return getName().contains("Booster Pack");
	}
	
	public boolean isTheme()
	{
		return getName().startsWith("Theme ");
	}
	
	public boolean isPrerelease()
	{
		return getName().startsWith("Prerelease ");
	}
	
	public boolean isSet()
	{
		return getName().startsWith("Set ");
	}
	
	public boolean isBox()
	{
		return getName().contains("Booster Box") || getName().contains("Booster Display") ;
	}
	
	public boolean isCollector()
	{
		return getName().contains("Collector");
	}
	
	public boolean isDraft()
	{
		return getName().contains("Draft");
	}
	
	public boolean isCase()
	{
		return getName().endsWith("Case");
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
