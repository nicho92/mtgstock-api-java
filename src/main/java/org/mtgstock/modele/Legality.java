package org.mtgstock.modele;

import org.mtgstock.tools.MTGStockConstants.FORMAT;

public class Legality {

	private FORMAT format;
	private String legal;
	
	public Legality() {
	
	}
	
	public Legality(FORMAT format, String legality) {
		super();
		this.format = format;
		this.legal = legality;
	}

	public FORMAT getFormat() {
		return format;
	}
	public void setFormat(FORMAT format) {
		this.format = format;
	}
	public String getLegal() {
		return legal;
	}
	public void setLegal(String legality) {
		this.legal = legality;
	}
	
	@Override
	public String toString() {
		return getFormat()+":"+getLegal();
	}
	
	@Override
	public int hashCode() {
		return getFormat().hashCode();
	}
	
	
}
