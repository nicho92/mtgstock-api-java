package org.api.mtgstock.modele;

public class SearchResult {

	private Integer id;
	private String name;
	
	public SearchResult() {
	}
	
	
	@Override
	public String toString() {
		return getName();
	}
	
	public SearchResult(Integer id, String name) {
		this.id = id;
		this.name = name;
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
		
}
