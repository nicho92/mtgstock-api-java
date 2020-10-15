package org.mtgstock.modele;

import java.util.Date;

public class Tournament {

	private Integer id;
	private String name;
	private Integer numPlayers;
	private String tournamentType;
	private Date date;
	
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
	public Integer getNumPlayers() {
		return numPlayers;
	}
	public void setNumPlayers(Integer numPlayers) {
		this.numPlayers = numPlayers;
	}
	public String getTournamentType() {
		return tournamentType;
	}
	public void setTournamentType(String tournamentType) {
		this.tournamentType = tournamentType;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
