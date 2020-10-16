package org.mtgstock.services;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mtgstock.modele.Archetype;
import org.mtgstock.modele.CardDetails;
import org.mtgstock.modele.DeckCard;
import org.mtgstock.modele.Legality;
import org.mtgstock.modele.Print;
import org.mtgstock.modele.Set;
import org.mtgstock.tools.MTGStockConstants;
import org.mtgstock.tools.MTGStockConstants.FORMAT;

import com.google.gson.JsonObject;

public abstract class AbstractMTGStockService {
	

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



	protected Logger logger = LogManager.getLogger(this.getClass());

	
	

	protected Print parsePrintFor(JsonObject obj) {
		Print p = new Print();
			  p.setId(obj.get(ID).getAsInt());
			  p.setName(obj.get(NAME).getAsString());
			  p.setRarity(obj.get(RARITY).getAsString());
			
			  if(obj.get(RESERVED)!=null)
				  p.setReserved(obj.get(RESERVED).getAsBoolean());
			 
			  if(obj.get("set_id")!=null)
				  p.setSetId(obj.get("set_id").getAsInt());
			 
			  
			  if(obj.get("set_name")!=null)
				 p.setSetName(obj.get("set_name").getAsString());
			  
			  
			  if(obj.get("icon_class")!=null && !obj.get("icon_class").isJsonNull())
				  p.setIconClass(obj.get("icon_class").getAsString());

			  
			  if(obj.get("set_type")!=null)
				  p.setSetType(obj.get("set_type").getAsString());
			  
			  if(obj.get("include_default")!=null)
					 p.setIncludeDefault(obj.get("include_default").getAsBoolean());
			
			  p.setExtendedArt(obj.get("name").getAsString().contains(MTGStockConstants.EXTENDED_ART));
			  p.setOversized(obj.get("name").getAsString().contains(MTGStockConstants.OVERSIZED));
			  p.setBorderless(obj.get("name").getAsString().contains(MTGStockConstants.BORDERLESS));
			  p.setShowcase(obj.get("name").getAsString().contains(MTGStockConstants.SHOWCASE));
			  
			 
			  try {
				  
				  for(String key : obj.get("legal").getAsJsonObject().keySet())
					  p.getLegal().add(new Legality(key, obj.get("legal").getAsJsonObject().get(key).getAsString()));
			  
			  }
			  catch(Exception e)
			  {
				  
			  }
			  
			  try {
				p.setImage(obj.get(IMAGE).getAsString());
				} catch (Exception e) {
					//do nothing
				}
			 
		
		return p;
	}
	
	protected Set parseSetFor(JsonObject o) {
		Set set = new Set();
			set.setId(o.get(ID).getAsInt());
			set.setName(o.get(NAME).getAsString());
			set.setIconClass(o.get("icon_class").getAsString());
			set.setSetType(o.get("set_type").getAsString());
			
			try {
				set.setAbbrevation(o.get("abbreviation").getAsString());
			}
			catch(Exception e)
			{
				//do nothing
			}
			
		
		
		return set;
	}


	protected CardDetails parseCardFor(JsonObject o) {
		
		CardDetails c = new CardDetails();
			 c.setId(o.get(ID).getAsInt());
			 c.setCmc(o.get(CMC).getAsInt());
			 c.setCost(o.get("cost").getAsString());
			 o.get("legal").getAsJsonObject().entrySet().forEach(e->c.getLegal().add(new Legality(e.getKey(), e.getValue().getAsString())));
			 c.setLowestPrint(o.get(LOWEST_PRINT).getAsInt());
			 c.setName(o.get(NAME).getAsString());
			 c.setOracle(o.get("oracle").getAsString());
			 c.setPwrtgh(o.get("pwrtgh").getAsString());
			 c.setReserved(o.get(RESERVED).getAsBoolean());
			 c.setSubtype(o.get("subtype").getAsString());
			 c.setSupertype(o.get("supertype").getAsString());
			 
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
	
	
	
}
