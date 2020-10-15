package org.mtgstock.modele;

public class Legality {

	private String format;
	private String legal;
	
	public Legality() {
	
	}
	
	public Legality(String format, String legality) {
		super();
		this.format = format;
		this.legal = legality;
	}

	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
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
