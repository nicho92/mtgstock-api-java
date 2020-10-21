package org.mtgstock.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mtgstock.modele.EntryValue;
import org.mtgstock.modele.HandyList;
import org.mtgstock.modele.LowHighValues;
import org.mtgstock.modele.Metagame;
import org.mtgstock.modele.Played;
import org.mtgstock.modele.Print;
import org.mtgstock.modele.CardSet;
import org.mtgstock.modele.SetPrices;
import org.mtgstock.tools.MTGStockConstants;
import org.mtgstock.tools.MTGStockConstants.FORMAT;
import org.mtgstock.tools.MTGStockConstants.PRICES;
import org.mtgstock.tools.Tools;
import org.mtgstock.tools.URLTools;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class AnalyticsService extends AbstractMTGStockService {

	

	public List<HandyList> listHandyList()
	{
		String url=MTGStockConstants.MTGSTOCK_API_URI+"/lists";
		logger.debug("getting HandyList at " + url);
		 List<HandyList> ret = new ArrayList<>();
		 
		 try {
			URLTools.extractJson(url).getAsJsonArray().forEach(e->{
				HandyList l = new HandyList();
				
				l.setId(e.getAsJsonObject().get(ID).getAsInt());
				l.setName(e.getAsJsonObject().get(NAME).getAsString());
				
				ret.add(l);
			});
		 }catch(Exception e)
		 {
			 logger.error("Error Getting handyList at " + url);
		 }
		 
		 return ret;
	}
	
	
	public List<Print> listPrintsForHandyList(HandyList hl)
	{
		return listPrintsForHandyList(hl.getId());
	}
	
	public List<Print> listPrintsForHandyList(Integer hl)
	{
		String url=MTGStockConstants.MTGSTOCK_API_URI+"/lists/"+hl;
		logger.debug("listing HandyList prints at " + url);
		List<Print> ret = new ArrayList<>();
		
		 try {
				for(JsonElement e : URLTools.extractJson(url).getAsJsonObject().get(PRINT+"s").getAsJsonArray())
				{
					ret.add(parsePrintFor(e.getAsJsonObject()));
				}
			 }catch(Exception e)
			 {
				 logger.error("Error Getting handyList at " + url);
			 }
		
		return ret;
		
	}
	


	public List<LowHighValues> listAllTimes()
	{
		List<LowHighValues> ret = new ArrayList<>();
		
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/analytics/alltime";
		logger.debug("getting low/high value at " + url);
		try {
			JsonArray arr = URLTools.extractJson(url).getAsJsonArray();
			
			for(JsonElement el : arr)
			{
				JsonObject obj = el.getAsJsonObject();
				LowHighValues val = new LowHighValues();
							  val.setType(PRICES.valueOf(obj.get(TYPE).getAsString().toUpperCase()));
							  val.setPrint(parsePrintFor(obj.get(PRINT).getAsJsonObject()));
							  val.setPrice(new EntryValue<>(Tools.initDate(obj.get(PRICE).getAsJsonObject().get(DATE).getAsString(),DATE_FORMAT),
									  					    obj.get(PRICE).getAsJsonObject().get(AVG).getAsDouble()
									  		));
				ret.add(val);
			}
			
			
		} catch (IOException e) {
			logger.error("Error getting low/hig values",e);
		}
		
		return ret;
	}
	
	


	public SetPrices getExpectedValue(CardSet s)
	{
		Optional<SetPrices> opt = getExpectedValues().stream().filter(sp->sp.getSet().getName().equals(s.getName())).findAny();
		
		if(opt.isPresent())
			return opt.get();
		else
			return null;
		
	}
	
	public List<SetPrices> getExpectedValues()
	{
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/analytics/expectedvalue";
		List<SetPrices> setp = new ArrayList<>();
		
		try {
			JsonArray arr = URLTools.extractJson(url).getAsJsonArray();
			
			
			for(JsonElement el : arr)
			{
				SetPrices p = new SetPrices();
				p.setSet(parseSetFor(el.getAsJsonObject().get(CARD_SET).getAsJsonObject()));
				JsonObject prices = el.getAsJsonObject().get("prices").getAsJsonObject();
				prices.keySet().stream().filter(s->!s.equalsIgnoreCase("num")).forEach(key->p.put(PRICES.valueOf(key.toUpperCase()), prices.get(key).getAsDouble()));
				
				if(prices.get("num")!=null)
					p.setNum(prices.get("num").getAsInt());
				
				setp.add(p);
			}
			
		} catch (IOException e) {
			logger.error("error getting expected value at " + url, e);
		}
		
		
		return setp;
		
	}
	
	public List<Played> getMostPlayedCard(FORMAT f)
	{
		List<Played> ret = new ArrayList<>();
		int id=Tools.getFormatCode(f);
		
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/analytics/mostplayed/"+id;
		
		logger.debug("get MostPlayedCard to " + url);
		try {
			JsonArray arr = URLTools.extractJson(url).getAsJsonObject().get(MOSTPLAYED).getAsJsonArray();
			
			arr.forEach(k->{
				Played p = new Played();
				   	   p.setName(k.getAsJsonObject().get(CARD).getAsJsonObject().get(NAME).getAsString());
					   p.setId(k.getAsJsonObject().get(CARD).getAsJsonObject().get(PRINT).getAsJsonObject().get(ID).getAsInt());
					  
					   if(k.getAsJsonObject().get(CARD).getAsJsonObject().get(PRINT).getAsJsonObject().get(IMAGE)!=null)
						   p.setImage(k.getAsJsonObject().get(CARD).getAsJsonObject().get(PRINT).getAsJsonObject().get(IMAGE).getAsString());
					   
					   
					   p.setQuantity(k.getAsJsonObject().get(QUANTITY).getAsInt());
					   
					   if(!k.getAsJsonObject().get(CARD).getAsJsonObject().get(PRINT).getAsJsonObject().get(LATEST_PRICE).getAsJsonObject().get(AVG).isJsonNull())
						   p.setAvgPrice(k.getAsJsonObject().get(CARD).getAsJsonObject().get(PRINT).getAsJsonObject().get(LATEST_PRICE).getAsJsonObject().get(AVG).getAsDouble());
				ret.add(p);
			});
		} catch (IOException e) {
			logger.error("Error getting mostplayedCard at " + url + " : " + e);
		}
		return ret;
	}
	
	
	public List<Metagame> getMetagamesFor(FORMAT f)
	{
		List<Metagame> metas = new ArrayList<>();
		
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/analytics/metagame/"+Tools.getFormatCode(f);
		
		
		try {
			JsonArray arr = URLTools.extractJson(url).getAsJsonArray();
			
			
			for(JsonElement e : arr)
			{
				Metagame m = new Metagame();
						 m.setArchetype(parseArchetypeFor(e.getAsJsonObject().get(ARCHETYPE).getAsJsonObject()));
						 m.setDate(Tools.initDate(e.getAsJsonObject().get(DATE).getAsString(),DATE_FORMAT));
						 m.setFormat(f);
						 m.setTotal(e.getAsJsonObject().get(TOTAL).getAsInt());
						 if(e.getAsJsonObject().get(PLACING)!=null)
							 m.setPlacings(e.getAsJsonObject().get(PLACING).getAsInt());
						 
	
				metas.add(m);
			}
			
			
		} catch (IOException e) {
			logger.error("Error getting metagames analytics for " + f,e);
		}
		
		
		
		return metas;
	}
	
	
}
