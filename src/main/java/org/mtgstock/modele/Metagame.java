package org.mtgstock.modele;

import java.util.Date;

import org.mtgstock.tools.MTGStockConstants.FORMAT;

public class Metagame {

	private Archetype archetype;
	private Date date;
	private FORMAT format;
	private Integer placings;
	private Integer total;
	
	
	@Override
	public String toString() {
		return String.valueOf(getArchetype());
	}
	
	
	public Archetype getArchetype() {
		return archetype;
	}
	public void setArchetype(Archetype archetype) {
		this.archetype = archetype;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public FORMAT getFormat() {
		return format;
	}
	public void setFormat(FORMAT format) {
		this.format = format;
	}
	public Integer getPlacings() {
		return placings;
	}
	public void setPlacings(Integer placings) {
		this.placings = placings;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}

	
	
}
