package org.api.mtgstock.modele;

import java.util.Date;

public class CardSet {

	private Integer id;
	private String name;
	private String abbrevation;
	private String iconClass;
	private String setType;
	private Date date;
	private CardSet extraSet;
	private String slug;
	
	@Override
	public String toString() {
		return getName();
	}
	
	public String getSlug() {
		return slug;
	}
	
	public void setSlug(String slug) {
		this.slug = slug;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbrevation() {
		return abbrevation;
	}
	public void setAbbrevation(String abbrevation) {
		this.abbrevation = abbrevation;
	}
	public String getIconClass() {
		return iconClass;
	}
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
	public String getSetType() {
		return setType;
	}
	public void setSetType(String setType) {
		this.setType = setType;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public void setExtraSet(CardSet extra) {
		this.extraSet=extra;
		
	}
	
	public CardSet getExtraSet() {
		return extraSet;
	}
	
	
	
	
}
