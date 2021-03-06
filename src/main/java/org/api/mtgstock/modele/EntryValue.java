package org.api.mtgstock.modele;

public class EntryValue<T,V> {

	private T key;
	private V value;
	
	public EntryValue() {
		//do nothing
	}
	
	@Override
	public String toString() {
		return key + ":" + value;
	}
	
	public EntryValue(T key, V value) {
		this.value = value;
		this.key = key;
	}

	
	public T getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}
	


}
