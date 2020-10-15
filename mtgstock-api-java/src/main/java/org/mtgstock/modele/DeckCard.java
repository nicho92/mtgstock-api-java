package org.mtgstock.modele;

public class DeckCard {

	private String cardType;
	private String name;
	private Integer cmc;
	private String color;
	private Set set;
	
	
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
	public Set getSet() {
		return set;
	}
	public void setSet(Set set) {
		this.set = set;
	}
	
	
	
	
}
