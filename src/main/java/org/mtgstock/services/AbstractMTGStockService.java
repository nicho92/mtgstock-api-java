package org.mtgstock.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mtgstock.modele.Archetype;
import org.mtgstock.modele.CardDetails;
import org.mtgstock.modele.CardSet;
import org.mtgstock.modele.DeckCard;
import org.mtgstock.modele.EntryValue;
import org.mtgstock.modele.Interest;
import org.mtgstock.modele.Legality;
import org.mtgstock.modele.PriceHash;
import org.mtgstock.modele.Print;
import org.mtgstock.tools.MTGStockConstants;
import org.mtgstock.tools.MTGStockConstants.CATEGORY;
import org.mtgstock.tools.MTGStockConstants.FORMAT;
import org.mtgstock.tools.MTGStockConstants.PRICES;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class AbstractMTGStockService {
	
	protected static final String ORACLE = "oracle";
	protected static final String PWRTGH = "pwrtgh";
	protected static final String SUPERTYPE = "supertype";
	protected static final String SUBTYPE = "subtype";
	protected static final String SUM = "sum";
	protected static final String PREVIOUS_PRICE = "previous_price";
	protected static final String LAST_WEEK_PRICE = "last_week_price";
	protected static final String COST = "cost";
	protected static final String ABBREVIATION = "abbreviation";
	protected static final String INCLUDE_DEFAULT = "include_default";
	protected static final String SET_ID = "set_id";
	protected static final String SET_NAME = "set_name";
	protected static final String ICON_CLASS = "icon_class";
	protected static final String SET_TYPE = "set_type";
	protected static final String TYPE = "type";
	protected static final String LEGAL = "legal";
	protected static final String NORMAL = "normal";
	protected static final String LOWEST_PRINT = "lowest_print";
	protected static final String SET = "set";
	protected static final String CARD_TYPE = "card_type";
	protected static final String CMC = "cmc";
	protected static final String COLOR = "color";
	protected static final String RESERVED = "reserved";
	protected static final String AVG = "avg";
	protected static final String NAME = "name";
	protected static final String QUANTITY = "quantity";
	protected static final String CARD = "card";
	protected static final String PRINT = "print";
	protected static final String LATEST_PRICE = "latest_price";
	protected static final String IMAGE = "image";
	protected static final String ID = "id";
	protected static final String PAST_PRICE = "past_price";
	protected static final String PRESENT_PRICE = "present_price";
	protected static final String PERCENTAGE = "percentage";
	protected static final String INTEREST_TYPE = "interest_type";
	protected static final String DATE = "date";
	protected static final String URL = "url";
	protected static final String LATEST_PRICE_MM = "latest_price_mm";
	protected static final String LATEST_PRICE_MKM = "latest_price_mkm";
	protected static final String LATEST_PRICE_CK = "latest_price_ck";
	protected static final String PRICE = "price";
	protected static final String ALL_TIME_HIGH = "all_time_high";
	protected static final String ALL_TIME_LOW = "all_time_low";
	protected static final String CARD_SET = "card_set";
	protected static final String TCG_URL = "tcg_url";
	protected static final String TCG_ID = "tcg_id";
	protected static final String MKM_URL = "mkm_url";
	protected static final String MKM_ID = "mkm_id";
	protected static final String IMAGE_FLIP = "image_flip";
	protected static final String FLIP = "flip";
	protected static final String FOIL = "foil";
	protected static final String RARITY = "rarity";
	protected static final String SETS = "sets";
	protected static final String MULTIVERSE_ID = "multiverse_id";
	protected static final String SIMILARITY = "similarity";
	protected static final String MOSTPLAYED = "mostplayed";
	protected static final String LOW = "low";
	protected static final String OLD = "old";
	protected static final String NUM_PLAYERS = "num_players";
	protected static final String TOURNAMENTTYPE = "tournamenttype";
	protected static final String POSITION = "position";
	protected static final String PLAYER = "player";
	protected static final String DECKS = "decks";
	protected static final String ARCHETYPE = "archetype";
	protected static final String TOTAL = "total";
	protected static final String PLACING = "placing";
	protected static final String PRICE_HASH = "price_hash";

	protected static final String DATE_FORMAT = "yyyy-MM-dd";



	protected Logger logger = LogManager.getLogger(this.getClass());

	
	

	
	protected PriceHash parsePriceHashFor(JsonObject obj, MTGStockConstants.RARITY r) 
	{
		PriceHash ph = new PriceHash();
				  ph.setRarity(r);
				  obj.get(AVG).getAsJsonObject().keySet().forEach(k->ph.getAvg().add(new EntryValue<>(PRICES.valueOf(k.toUpperCase()), Double.parseDouble(obj.get(AVG).getAsJsonObject().get(k).getAsString()))));
				  obj.get(SUM).getAsJsonObject().keySet().forEach(k->ph.getSum().add(new EntryValue<>(PRICES.valueOf(k.toUpperCase()), Double.parseDouble(obj.get(SUM).getAsJsonObject().get(k).getAsString()))));
		return ph;
	}
	

	protected Print parsePrintFor(JsonObject obj) {
		Print p = new Print();
			  p.setId(obj.get(ID).getAsInt());
			  p.setName(obj.get(NAME).getAsString());
			 
			  try{
				  p.setRarity(MTGStockConstants.RARITY.valueOf(obj.get(RARITY).getAsString()));
			  }
			  catch(Exception e)
			  {
				  logger.trace("Rarity "  + obj.get(RARITY) + " for " + p.getName() +" doesn't exist");
			  }
			  
			  
			  if(obj.get(RESERVED)!=null)
				  p.setReserved(obj.get(RESERVED).getAsBoolean());
			 
			  if(obj.get(SET_ID)!=null)
				  p.setSetId(obj.get(SET_ID).getAsInt());
			 
			  
			  if(obj.get(FOIL)!=null)
				  p.setFoil(obj.get(FOIL).getAsBoolean());
			  
			  if(obj.get(SET_NAME)!=null)
				  p.setSetName(obj.get(SET_NAME).getAsString());
			  
			  
			  if(obj.get(ICON_CLASS)!=null && !obj.get(ICON_CLASS).isJsonNull())
				  p.setIconClass(obj.get(ICON_CLASS).getAsString());

			  
			  if(obj.get(SET_TYPE)!=null)
				  p.setSetType(obj.get(SET_TYPE).getAsString());
			  
			  if(obj.get(INCLUDE_DEFAULT)!=null)
					 p.setIncludeDefault(obj.get(INCLUDE_DEFAULT).getAsBoolean());
			
			  
			  if(obj.get(LAST_WEEK_PRICE)!=null && !obj.get(LAST_WEEK_PRICE).isJsonNull())
			  	  	p.setLastWeekPrice(obj.get(LAST_WEEK_PRICE).getAsDouble());
			  else
				  	p.setLastWeekPrice(0.0);
			  
			  
			  if(obj.get(PREVIOUS_PRICE)!=null && !obj.get(PREVIOUS_PRICE).isJsonNull())
				  	p.setLastWeekPreviousPrice(obj.get(PREVIOUS_PRICE).getAsDouble());
			  else
				  p.setLastWeekPreviousPrice(0.0);
			  
			  
			  
			  p.setExtendedArt(obj.get(NAME).getAsString().contains(MTGStockConstants.EXTENDED_ART));
			  p.setOversized(obj.get(NAME).getAsString().contains(MTGStockConstants.OVERSIZED));
			  p.setBorderless(obj.get(NAME).getAsString().contains(MTGStockConstants.BORDERLESS));
			  p.setShowcase(obj.get(NAME).getAsString().contains(MTGStockConstants.SHOWCASE));
			  
			  
			  if(obj.get(LATEST_PRICE)!=null)
			  {
				  
				  if(obj.get(LATEST_PRICE).isJsonObject()) 
				  {
					  obj.get(LATEST_PRICE).getAsJsonObject().keySet().forEach(key->{
						  try {
							  p.getLatestPrices().put(MTGStockConstants.PRICES.valueOf(key.toUpperCase()),obj.get(LATEST_PRICE).getAsJsonObject().get(key).getAsDouble());
						  }
						  catch(Exception e)
						  {
							  p.getLatestPrices().put(MTGStockConstants.PRICES.valueOf(key.toUpperCase()),0.0);
						  }
					  });
				  }
				  else
				  {
					  p.getLatestPrices().put(PRICES.AVG, obj.get(LATEST_PRICE).getAsDouble());
				  }
			  }
			  
			  if(obj.get(LEGAL)!=null && obj.get(LEGAL).isJsonObject())
				  for(String key : obj.get(LEGAL).getAsJsonObject().keySet())
				  {
					  try {
						p.getLegal().add(new Legality(FORMAT.valueOf(key.toUpperCase()), obj.get(LEGAL).getAsJsonObject().get(key).getAsString()));
					} catch (Exception e) {
						logger.error("Not legality found for " + key);
					}
				  }
			
			  
			  try {
				p.setImage(obj.get(IMAGE).getAsString());
				} catch (Exception e) {
					//do nothing
				}
			 
		
		return p;
	}
	
	protected CardSet parseSetFor(JsonObject o) {
		CardSet set = new CardSet();
			set.setId(o.get(ID).getAsInt());
			set.setName(o.get(NAME).getAsString());
			set.setIconClass(o.get(ICON_CLASS).getAsString());
			set.setSetType(o.get(SET_TYPE).getAsString());
			
			try {
				set.setAbbrevation(o.get(ABBREVIATION).getAsString());
			}
			catch(Exception e)
			{
				//do nothing

				if(set.getId()==305)
					set.setAbbrevation("FBB");
				
				if(set.getId()==306)
					set.setAbbrevation("FWB");
				
				if(set.getId()==370)
					set.setAbbrevation("PLIST");
				
				if(set.getId()==117)
					set.setAbbrevation("PPRO");
				
				if(set.getId()==116)
					set.setAbbrevation("PPRE");
				
				if(set.getId()==377)
					set.setAbbrevation("AMH1");
				
				if(set.getId()==351)
					set.setAbbrevation("PBBD");
				
				if(set.getId()==236)
					set.setAbbrevation("PCMP");
				
				if(set.getId()==355)
					set.setAbbrevation("M21");
				
				if(set.getId()==353)
					set.setAbbrevation("IKO");
				
				if(set.getId()==356)
					set.setAbbrevation("2XM");
				
				if(set.getId()==345)
					set.setAbbrevation("THB");
				
				if(set.getId()==304)
					set.setAbbrevation("DPA");
				
				if(set.getId()==333)
					set.setAbbrevation("CEI");
				
				if(set.getId()==342)
					set.setAbbrevation("CMB1");
				
				if(set.getId()==325)
					set.setAbbrevation("GK2");
				
				if(set.getId()==228)
					set.setAbbrevation("PCEL");
				
				if(set.getId()==367)
					set.setAbbrevation(SUM);
				
				if(set.getId()==245)
					set.setAbbrevation("UGIN");
				
				if(set.getId()==369)
					set.setAbbrevation("AZNR");
				
				if(set.getId()==276)
					set.setAbbrevation("PANA");
				
				if(set.getId()==375)
					set.setAbbrevation("PM21");
				
				if(set.getId()==374)
					set.setAbbrevation("PM20");
				
				if(set.getId()==372)
					set.setAbbrevation("PIKO");
				
				if(set.getId()==373)
					set.setAbbrevation("PTHB");
				
				if(set.getId()==371)
					set.setAbbrevation("PELD");
				
				if(set.getId()==379)
					set.setAbbrevation("PZNR");
			}
			
		
		
		return set;
	}


	protected CardDetails parseCardFor(JsonObject o) {
		
		CardDetails c = new CardDetails();
			 c.setId(o.get(ID).getAsInt());
			 c.setLowestPrint(o.get(LOWEST_PRINT).getAsInt());
			 c.setName(o.get(NAME).getAsString());
			 
			 if(!o.get(CMC).isJsonNull())
				 c.setCmc(o.get(CMC).getAsInt());
			 
			 if(!o.get(COST).isJsonNull())
				 c.setCost(o.get(COST).getAsString());
			
			 if(!o.get(LEGAL).isJsonNull())
				 o.get(LEGAL).getAsJsonObject().entrySet().forEach(e->c.getLegal().add(new Legality(FORMAT.valueOf(e.getKey().toUpperCase()), e.getValue().getAsString())));
			
			 
			 if(!o.get(ORACLE).isJsonNull())
				 c.setOracle(o.get(ORACLE).getAsString());

			 if(!o.get(PWRTGH).isJsonNull())
				 c.setPwrtgh(o.get(PWRTGH).getAsString());
			 
			 if(!o.get(RESERVED).isJsonNull())
				 c.setReserved(o.get(RESERVED).getAsBoolean());
			 
			 if(!o.get(SUBTYPE).isJsonNull())
				 c.setSubtype(o.get(SUBTYPE).getAsString());
			
			 if(!o.get(SUPERTYPE).isJsonNull())
				 c.setSupertype(o.get(SUPERTYPE).getAsString());
			 
		return null;
	}


	protected Archetype parseArchetypeFor(JsonObject e) {
		Archetype at = new Archetype();
		at.setId(e.get(ID).getAsInt());
		at.setName(e.get(NAME).getAsString());
		
		if(e.get(OLD)!=null)
			at.setOld(e.get(OLD).getAsBoolean());
		
		return at;
	}
	
	protected DeckCard parseDeckCardFor(JsonObject o)
	{
		DeckCard d = new DeckCard();
				 d.setCardType(o.get(CARD_TYPE).getAsString());
				 d.setCmc(o.get(CMC).getAsInt());
				 d.setColor(o.get(COLOR).getAsString());
				 d.setName(o.get(NAME).getAsString());
				 d.setSet(parseSetFor(o.get(LOWEST_PRINT).getAsJsonObject().get(SET).getAsJsonObject()));
		return d;
	}
	
	protected List<Interest> parseInterestFor(CATEGORY c,boolean foil,JsonObject interests)
	{
		JsonArray arrs = interests.get( (foil)?FOIL:NORMAL).getAsJsonArray(); 
		List<Interest> ret = new ArrayList<>();
		
		for(JsonElement e : arrs)
		{
			JsonObject obj = e.getAsJsonObject();
			Interest t = new Interest();
					 t.setCategory(c);
					 t.setDate(new Date(obj.get(DATE).getAsLong()));
				     t.setInterestType(obj.get(INTEREST_TYPE).getAsString());
					 t.setPercentage(obj.get(PERCENTAGE).getAsDouble());
					 t.setPricePresent(obj.get(PRESENT_PRICE).getAsDouble());
					 t.setPricePast(obj.get(PAST_PRICE).getAsDouble());
					 t.setPrint(parsePrintFor(obj.get(PRINT).getAsJsonObject()));
					 t.setFoil(foil);
					 
			 ret.add(t);
		}
		return ret;
	}
	
	
}
