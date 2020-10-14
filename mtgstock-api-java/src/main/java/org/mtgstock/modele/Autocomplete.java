package org.mtgstock.modele;

public class Autocomplete {

	private Integer id;
	private String name;
	private Double similarity;
	
	
	public Autocomplete() {
	}
	
	public Autocomplete(Integer id, String name, Double similarity) {
		this.id = id;
		this.name = name;
		this.similarity = similarity;
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
	public Double getSimilarity() {
		return similarity;
	}
	public void setSimilarity(Double similarity) {
		this.similarity = similarity;
	}
	
	
	
}
