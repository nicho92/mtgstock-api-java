package org.mtgstock.modele;

public class DeckCard {

	private String cardType;
	private String name;
	private Integer cmc;
	private String color;
	private CardSet set;
	
	
	@Override
	public String toString() {
		return getName();
	}
	
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCmc() {
		return cmc;
	}
	public void setCmc(Integer cmc) {
		this.cmc = cmc;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public CardSet getSet() {
		return set;
	}
	public void setSet(CardSet set) {
		this.set = set;
	}
	
	
	
	
}
