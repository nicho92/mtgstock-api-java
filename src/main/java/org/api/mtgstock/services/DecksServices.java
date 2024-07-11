package org.api.mtgstock.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.api.mtgstock.modele.Archetype;
import org.api.mtgstock.modele.Deck;
import org.api.mtgstock.modele.DeckInfo;
import org.api.mtgstock.modele.Tournament;
import org.api.mtgstock.tools.MTGStockConstants;
import org.api.mtgstock.tools.MTGStockConstants.FORMAT;
import org.api.mtgstock.tools.Tools;

import com.google.gson.JsonElement;

public class DecksServices extends AbstractMTGStockService {

	private static final String SIDEBOARD = "sideboard";
	private static final String MAINBOARD = "mainboard";



	public List<DeckInfo> listDeckForTournament(Integer id)
	{
		List<DeckInfo> ret = new ArrayList<>();
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/tournaments/"+id;
		try {
			var t = client.extractJson(url).getAsJsonObject();
			
			for(JsonElement e : t.get(DECKS).getAsJsonArray())
			{
				var at = new DeckInfo();
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

	public Deck getDecksDetails(DeckInfo di)
	{
		return getDecksDetails(di.getId());
	}
	
	
	public Deck getDecksDetails(Integer id)
	{
		var d = new Deck();
		
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/decks/"+id;
		logger.debug("get deck at " + url);
		try {
			var obj = client.extractJson(url).getAsJsonObject();
			
				d.setId(id);
				d.setName(obj.get(NAME).getAsString());
				d.setPlayer(obj.get(PLAYER).getAsString());
				d.setArchetype(parseArchetypeFor(obj.get(ARCHETYPE).getAsJsonObject()));
				d.setFormat(FORMAT.valueOf(obj.get("format").getAsJsonObject().get(NAME).getAsString().toUpperCase()));
				d.setDate(Tools.initDate(obj.get(DATE).getAsLong()));
				obj.get(MAINBOARD).getAsJsonArray().forEach(je->d.getMainboard().put(parseDeckCardFor(je.getAsJsonObject().get(CARD).getAsJsonObject()), je.getAsJsonObject().get(QUANTITY).getAsInt()));
				obj.get(SIDEBOARD).getAsJsonArray().forEach(je->d.getSideboard().put(parseDeckCardFor(je.getAsJsonObject().get(CARD).getAsJsonObject()), je.getAsJsonObject().get(QUANTITY).getAsInt()));
		}
		catch(Exception e)
		{
			logger.error(e);
		}
		return d;
	}
	
	public List<Tournament> listTournaments(FORMAT f)
	{
		List<Tournament> ret = new ArrayList<>();
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/tournaments/format/"+Tools.getFormatCode(f);
		logger.debug("list tournament at " + url);
		try {
			var arr = client.extractJson(url).getAsJsonArray();
			
			for(JsonElement e : arr)
			{
				var at = new Tournament();
						   at.setId(e.getAsJsonObject().get(ID).getAsInt());
						   at.setName(e.getAsJsonObject().get(NAME).getAsString());
						   at.setTournamentType(e.getAsJsonObject().get(TOURNAMENTTYPE).getAsString());
						   
						   if(!e.getAsJsonObject().get(DATE).isJsonNull())
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
		
		var url = MTGStockConstants.MTGSTOCK_API_URI+"/archetypes/format/"+Tools.getFormatCode(f);
		logger.debug("list archetypes at " + url);
		
		try {
			var arr = client.extractJson(url).getAsJsonArray();
			
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
