package org.mtgstock.modele;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.mtgstock.tools.MTGStockConstants.FORMAT;

public class Deck {

	private Integer id;
	private String name;
	private String player;
	private Object commander;
	private Object commanderPartner;
	private Integer position;
	private Integer tournamentId;
	private Date date;
	private Archetype archetype;
	private FORMAT format;
	private Map<DeckCard,Integer> mainboard;
	private Map<DeckCard,Integer> sideboard;
	
	public Deck() {
		mainboard= new HashMap<>();
		sideboard = new HashMap<>();
	}
	
	
	@Override
	public String toString() {
		return getName();
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
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public Object getCommander() {
		return commander;
	}
	public void setCommander(Object commander) {
		this.commander = commander;
	}
	public Object getCommanderPartner() {
		return commanderPartner;
	}
	public void setCommanderPartner(Object commanderPartner) {
		this.commanderPartner = commanderPartner;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Integer getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(Integer tournamentId) {
		this.tournamentId = tournamentId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Archetype getArchetype() {
		return archetype;
	}
	public void setArchetype(Archetype archetype) {
		this.archetype = archetype;
	}
	public FORMAT getFormat() {
		return format;
	}
	public void setFormat(FORMAT format) {
		this.format = format;
	}
	public Map<DeckCard, Integer> getMainboard() {
		return mainboard;
	}
	public void setMainboard(Map<DeckCard, Integer> mainboard) {
		this.mainboard = mainboard;
	}
	public Map<DeckCard, Integer> getSideboard() {
		return sideboard;
	}
	public void setSideboard(Map<DeckCard, Integer> sideboard) {
		this.sideboard = sideboard;
	}
	
	
	
}
