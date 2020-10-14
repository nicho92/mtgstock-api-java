package org.mtgstock.modele;

import java.util.Date;

public class EntryValue {

	private String key;
	private Double value;
	private Date date;
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public int hashCode() {
		return getKey().hashCode();
	}
	
}
