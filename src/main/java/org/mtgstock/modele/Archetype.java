package org.mtgstock.modele;

import org.mtgstock.tools.MTGStockConstants.FORMAT;

public class Archetype {

	
	private Integer id;
	private String name;
	private Boolean old;
	
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
	public Boolean getOld() {
		return old;
	}
	public void setOld(Boolean old) {
		this.old = old;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	
}
