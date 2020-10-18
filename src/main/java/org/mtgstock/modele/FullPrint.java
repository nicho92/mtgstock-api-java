package org.mtgstock.modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mtgstock.tools.MTGStockConstants.PRICES;

public class FullPrint extends Print {

	private Integer multiverseId;
	private Boolean flip;
	private String imageFlip;
	private Integer mkmId;
	private String mkmUrl;
	private Integer tcgId;
	private String tcgUrl;
	private CardDetails card;
	private CardSet cardSet;
	private EntryValue<Double,Date> allTimeLow;
	private EntryValue<Double,Date> allTimeHigh;
	
	private EntryValue<Double,String> latestPriceCardKingdom;
	private EntryValue<Double,Double> latestPriceMkm;
	private EntryValue<Double,String> latestPriceMiniatureMarket;
	private List<Print> sets;
	
	public FullPrint() {
		sets = new ArrayList<>();
	}
		
	public Print getPrintForSetId(int id)
	{
		return sets.stream().filter(p->p.getSetId()==id).findAny().orElse(null);
	}
	
	
	
	public EntryValue<Double,Date> getAllTimeHigh() {
		return allTimeHigh;
	}
	public void setAllTimeHigh(EntryValue<Double,Date> allTimeHigh) {
		this.allTimeHigh = allTimeHigh;
	}
	public Integer getMultiverseId() {
		return multiverseId;
	}
	public void setMultiverseId(Integer multiverseId) {
		this.multiverseId = multiverseId;
	}
	
	public Boolean getFlip() {
		return flip;
	}
	public void setFlip(Boolean flip) {
		this.flip = flip;
	}
	public String getImageFlip() {
		return imageFlip;
	}
	public void setImageFlip(String imageFlip) {
		this.imageFlip = imageFlip;
	}
	
	
	public Integer getMkmId() {
		return mkmId;
	}
	public void setMkmId(Integer mkmId) {
		this.mkmId = mkmId;
	}
	public String getMkmUrl() {
		return mkmUrl;
	}
	public void setMkmUrl(String mkmUrl) {
		this.mkmUrl = mkmUrl;
	}
	public Integer getTcgId() {
		return tcgId;
	}
	public void setTcgId(Integer tcgId) {
		this.tcgId = tcgId;
	}
	public String getTcgUrl() {
		return tcgUrl;
	}
	public void setTcgUrl(String tcgUrl) {
		this.tcgUrl = tcgUrl;
	}
	public CardDetails getCard() {
		return card;
	}
	public void setCard(CardDetails card) {
		this.card = card;
	}
	public CardSet getCardSet() {
		return cardSet;
	}
	public void setCardSet(CardSet cardSet) {
		this.cardSet = cardSet;
	}
	
	public List<Print> getSets() {
		return sets;
	}
	public void setSets(List<Print> sets) {
		this.sets = sets;
	}



	public EntryValue<Double,String> getLatestPriceCardKingdom() {
		return latestPriceCardKingdom;
	}


	public void setLatestPriceCardKingdom(EntryValue<Double,String> latestPriceCardKingdom) {
		this.latestPriceCardKingdom = latestPriceCardKingdom;
	}


	public EntryValue<Double,Double> getLatestPriceMkm() {
		return latestPriceMkm;
	}


	public void setLatestPriceMkm(EntryValue<Double,Double> latestPriceMkm) {
		this.latestPriceMkm = latestPriceMkm;
	}


	public EntryValue<Double,String> getLatestPriceMiniatureMarket() {
		return latestPriceMiniatureMarket;
	}


	public void setLatestPriceMiniatureMarket(EntryValue<Double,String> latestPriceMiniatureMarket) {
		this.latestPriceMiniatureMarket = latestPriceMiniatureMarket;
	}


	public void setAllTimeLow(EntryValue<Double,Date> allTimeLow) {
		this.allTimeLow = allTimeLow;
	}
	public EntryValue<Double,Date> getAllTimeLow() {
		return allTimeLow;
	}
	
	
	
	
}
