package org.mtgstock.modele;

import java.util.List;

public class FullPrint extends Print {

	private Integer multiverseId;
	private Boolean foil;
	private Boolean flip;
	private String imageFlip;
	private Integer mkmId;
	private String mkmUrl;
	private Integer tcgId;
	private String tcgUrl;
	private Card card;
	private CardSet cardSet;
	private EntryValue allTimeLow;
	private List<EntryValue> latestPrices;
	private List<EntryValue> latestPriceCardKingdom;
	private List<EntryValue> latestPriceMkm;
	private List<EntryValue> latestPriceMiniatureMarket;
	private List<CardSet> sets;
	
	public Integer getMultiverseId() {
		return multiverseId;
	}
	public void setMultiverseId(Integer multiverseId) {
		this.multiverseId = multiverseId;
	}
	public Boolean isFoil() {
		return foil;
	}
	public void setFoil(Boolean foil) {
		this.foil = foil;
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
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public CardSet getCardSet() {
		return cardSet;
	}
	public void setCardSet(CardSet cardSet) {
		this.cardSet = cardSet;
	}
	public EntryValue getAllTimeLow() {
		return allTimeLow;
	}
	public void setAllTimeLow(EntryValue allTimeLow) {
		this.allTimeLow = allTimeLow;
	}
	public List<EntryValue> getLatestPrices() {
		return latestPrices;
	}
	public void setLatestPrices(List<EntryValue> latestPrices) {
		this.latestPrices = latestPrices;
	}
	public List<EntryValue> getLatestPriceCardKingdom() {
		return latestPriceCardKingdom;
	}
	public void setLatestPriceCardKingdom(List<EntryValue> latestPriceCardKingdom) {
		this.latestPriceCardKingdom = latestPriceCardKingdom;
	}
	public List<EntryValue> getLatestPriceMkm() {
		return latestPriceMkm;
	}
	public void setLatestPriceMkm(List<EntryValue> latestPriceMkm) {
		this.latestPriceMkm = latestPriceMkm;
	}
	public List<EntryValue> getLatestPriceMiniatureMarket() {
		return latestPriceMiniatureMarket;
	}
	public void setLatestPriceMiniatureMarket(List<EntryValue> latestPriceMiniatureMarket) {
		this.latestPriceMiniatureMarket = latestPriceMiniatureMarket;
	}
	public List<CardSet> getSets() {
		return sets;
	}
	public void setSets(List<CardSet> sets) {
		this.sets = sets;
	}
	
	
	
	
}
