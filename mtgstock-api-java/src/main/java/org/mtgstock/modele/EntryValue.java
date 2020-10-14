package org.mtgstock.modele;

public class EntryValue<T,V> {

	private T value;
	private V date;
	
	public EntryValue() {
		//do nothing
	}
	
	
	
	public EntryValue(T value, V date) {
		this.value = value;
		this.date = date;
	}



	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public V getDate() {
		return date;
	}
	public void setDate(V date) {
		this.date = date;
	}
	
}
