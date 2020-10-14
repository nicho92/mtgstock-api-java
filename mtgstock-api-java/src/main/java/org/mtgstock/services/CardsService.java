package org.mtgstock.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mtgstock.modele.Card;
import org.mtgstock.modele.CardSet;
import org.mtgstock.modele.EntryValue;
import org.mtgstock.modele.FullPrint;
import org.mtgstock.modele.Legality;
import org.mtgstock.modele.Print;
import org.mtgstock.modele.SearchResult;
import org.mtgstock.tools.MTGStockConstants;
import org.mtgstock.tools.Tools;
import org.mtgstock.tools.URLTools;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class CardsService extends AbstractMTGStockService {
	
	
	public static void main(String[] args) throws IOException {
		FullPrint fp = new CardsService().getCard(49971);
		
		System.out.println(fp);
		
	}
	
	
	public List<SearchResult> search(String name)
	{
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/search/autocomplete/"+name;
		List<SearchResult> ret = new ArrayList<>();
		
		try {
			JsonArray arr = URLTools.extractJson(url).getAsJsonArray();
			for(JsonElement e : arr)
			{
				JsonObject oe = e.getAsJsonObject();
				ret.add(new SearchResult(oe.get("id").getAsInt(), oe.get("name").getAsString(), oe.get("similarity").getAsDouble()));
			}
		} catch (IOException e) {
			logger.error(e);
		}
		return ret;
	}

	public FullPrint getCard(SearchResult sr) throws IOException
	{
		return getCard(sr.getId());
	}
	
	public FullPrint getCard(Print p) throws IOException
	{
		return getCard(p.getId());
	}
	
	public List<CardSet> listSets()
	{
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/card_sets";
		
		logger.debug("getting sets at " + url);
		List<CardSet> sets = new ArrayList<>();
		
		
		try {
			for(JsonElement e : URLTools.extractJson(url).getAsJsonArray())
				sets.add(getSetFor(e.getAsJsonObject()));
		
		} catch (IOException e) {
			logger.error("Error gettings sets ",e);
			
		}
		
		return sets;
	}
	
	
	
	public FullPrint getCard(Integer id) throws IOException
	{
		
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/prints/"+id;
		
		logger.debug("read card at " + url);
		JsonObject o = URLTools.extractJson(url).getAsJsonObject();
		
		FullPrint fp = new FullPrint();
				  fp.setId(o.get("id").getAsInt());
				  fp.setName(o.get("name").getAsString());
				  
				  fp.setBorderless(o.get("name").getAsString().contains(MTGStockConstants.BORDERLESS));
				  fp.setExtendedArt(o.get("name").getAsString().contains(MTGStockConstants.EXTENDED_ART));
				  fp.setShowcase(o.get("name").getAsString().contains(MTGStockConstants.SHOWCASE));
				  fp.setOversized(o.get("name").getAsString().contains(MTGStockConstants.OVERSIZED));
				  fp.setRarity(o.get("rarity").getAsString());
				  fp.setFoil(o.get("foil").getAsBoolean());
				  fp.setFlip(o.get("flip").getAsBoolean());
				  fp.setImageFlip(o.get("image_flip").getAsString());
				  fp.setImage(o.get("image").getAsString());
				  fp.setMkmId(o.get("mkm_id").getAsInt());
				  fp.setMkmUrl(o.get("mkm_url").getAsString());
				  fp.setTcgId(o.get("tcg_id").getAsInt());
				  fp.setTcgUrl(o.get("tcg_url").getAsString());
				  fp.setCard(getCardFor(o.get("card").getAsJsonObject()));
				  fp.setCardSet(getSetFor(o.get("card_set").getAsJsonObject()));
				  fp.setAllTimeLow(new EntryValue<>(o.get("all_time_low").getAsJsonObject().get("avg").getAsDouble(),Tools.initDate(o.get("all_time_low").getAsJsonObject().get("date").getAsLong())));
				  fp.setAllTimeHigh(new EntryValue<>(o.get("all_time_high").getAsJsonObject().get("avg").getAsDouble(),Tools.initDate(o.get("all_time_high").getAsJsonObject().get("date").getAsLong())));
				  fp.setLatestPriceCardKingdom(new EntryValue<>(o.get("latest_price_ck").getAsJsonObject().get("price").getAsDouble(),o.get("latest_price_ck").getAsJsonObject().get("url").getAsString()));
				  fp.setLatestPriceMkm(new EntryValue<>(o.get("latest_price_mkm").getAsJsonObject().get("avg").getAsDouble(),o.get("latest_price_mkm").getAsJsonObject().get("low").getAsDouble()));
				  fp.setLatestPriceMiniatureMarket(new EntryValue<>(o.get("latest_price_mm").getAsJsonObject().get("price").getAsDouble(),o.get("latest_price_mm").getAsJsonObject().get("url").getAsString()));
				  
				  if(!o.get("multiverse_id").isJsonNull())
					  fp.setMultiverseId(o.get("multiverse_id").getAsInt());
				  
				  
				  o.get("latest_price").getAsJsonObject().keySet().forEach(key->{
					  try {
						  fp.getLatestPrices().add(new EntryValue<>(MTGStockConstants.PRICES.valueOf(key.toUpperCase()),o.get("latest_price").getAsJsonObject().get(key).getAsDouble()));
					  }
					  catch(Exception e)
					  {
						  //do nothing
					  }
					  
					  
				  });
		return fp;
	}


	private CardSet getSetFor(JsonObject o) {
		CardSet set = new CardSet();
			set.setId(o.get("id").getAsInt());
			set.setName(o.get("name").getAsString());
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


	private Card getCardFor(JsonObject o) {
		
		Card c = new Card();
			 c.setId(o.get("id").getAsInt());
			 c.setCmc(o.get("cmc").getAsInt());
			 c.setCost(o.get("cost").getAsString());
			 o.get("legal").getAsJsonObject().entrySet().forEach(e->c.getLegal().add(new Legality(e.getKey(), e.getValue().getAsString())));
			 c.setLowestPrint(o.get("lowest_print").getAsInt());
			 c.setName(o.get("name").getAsString());
			 c.setOracle(o.get("oracle").getAsString());
			 c.setPwrtgh(o.get("pwrtgh").getAsString());
			 c.setReserved(o.get("reserved").getAsBoolean());
			 c.setSubtype(o.get("subtype").getAsString());
			 c.setSupertype(o.get("supertype").getAsString());
			 
		return null;
	}
	
	
	
	
	
	
}
