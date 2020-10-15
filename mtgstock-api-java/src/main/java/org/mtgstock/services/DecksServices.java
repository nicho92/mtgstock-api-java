package org.mtgstock.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mtgstock.modele.Archetype;
import org.mtgstock.modele.Deck;
import org.mtgstock.modele.DeckInfo;
import org.mtgstock.modele.Tournament;
import org.mtgstock.tools.MTGStockConstants;
import org.mtgstock.tools.MTGStockConstants.FORMAT;
import org.mtgstock.tools.Tools;
import org.mtgstock.tools.URLTools;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class DecksServices extends AbstractMTGStockService {

	public List<DeckInfo> listDeckForTournament(Integer id)
	{
		List<DeckInfo> ret = new ArrayList<>();
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/tournaments/"+id;
		try {
			JsonObject t = URLTools.extractJson(url).getAsJsonObject();
			
			for(JsonElement e : t.get(DECKS).getAsJsonArray())
			{
				DeckInfo at = new DeckInfo();
						   at.setId(e.getAsJsonObject().get(ID).getAsInt());
						   at.setPlayerName(e.getAsJsonObject().get(PLAYER).getAsString());
						   
						   if(!e.getAsJsonObject().get(POSITION).isJsonNull())
							   at.setPosition(e.getAsJsonObject().get(POSITION).getAsInt());
						   
						   at.setArchetype(parseArchetypeFor(e.getAsJsonObject().get(ARCHETYPE).getAsJsonObject()));
						   
						   
				ret.add(at);
			}
		} catch (IOException e) {
			logger.error("error getting decks for tournament=" + id,e);
		}
			
		
		return ret;
	}
	
	public List<DeckInfo> listDeckForTournament(Tournament t)
	{
		return listDeckForTournament(t.getId());
	}

	
	public Deck getDecksDetails(DeckInfo d)
	{
		return new Deck();
	}
	
	public List<Tournament> listTournaments(FORMAT f)
	{
		List<Tournament> ret = new ArrayList<>();
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/tournaments/format/"+Tools.getFormatCode(f);
		try {
			JsonArray arr = URLTools.extractJson(url).getAsJsonArray();
			
			for(JsonElement e : arr)
			{
				Tournament at = new Tournament();
						   at.setId(e.getAsJsonObject().get(ID).getAsInt());
						   at.setName(e.getAsJsonObject().get(NAME).getAsString());
						   at.setTournamentType(e.getAsJsonObject().get(TOURNAMENTTYPE).getAsString());
						   at.setDate(Tools.initDate(e.getAsJsonObject().get(DATE).getAsLong()));
						   
						   if(!e.getAsJsonObject().get(NUM_PLAYERS).isJsonNull())
							   at.setNumPlayers(e.getAsJsonObject().get(NUM_PLAYERS).getAsInt());
						   
						  
				
				ret.add(at);
			}
		} catch (IOException e) {
			logger.error("error getting tournaments for " + f,e);
		}
			
		
		
		return ret;
	}
	
	

	public List<Archetype> listArchetypes(FORMAT f)
	{
		List<Archetype> ret = new ArrayList<>();
		
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/archetypes/format/"+Tools.getFormatCode(f);
		try {
			JsonArray arr = URLTools.extractJson(url).getAsJsonArray();
			
			for(JsonElement e : arr)
			{
				ret.add(parseArchetypeFor(e.getAsJsonObject()));
			}
			
			
		
		} catch (IOException e) {
			logger.error("error getting archetypes for " + f,e);
		}
		
		
		
		
		return ret;
	}
	
	
}
