package org.mtgstock.modele;

public class Deck {
	
	
	private int id;
	private Archetype archetype;
	private String playerName;
	private Integer position;
	
	
	@Override
	public String toString() {
		return (getArchetype()!=null)?getArchetype().getName():"";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Archetype getArchetype() {
		return archetype;
	}
	public void setArchetype(Archetype archetype) {
		this.archetype = archetype;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	
	
	
}
