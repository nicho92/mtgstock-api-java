package org.api.mtgstock.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.api.mtgstock.modele.CardSet;
import org.api.mtgstock.modele.EntryValue;
import org.api.mtgstock.modele.HandyList;
import org.api.mtgstock.modele.LowHighValues;
import org.api.mtgstock.modele.Metagame;
import org.api.mtgstock.modele.Played;
import org.api.mtgstock.modele.Print;
import org.api.mtgstock.modele.SetPrices;
import org.api.mtgstock.tools.MTGStockConstants;
import org.api.mtgstock.tools.MTGStockConstants.FORMAT;
import org.api.mtgstock.tools.MTGStockConstants.PRICES;
import org.api.mtgstock.tools.Tools;

import com.google.gson.JsonElement;

public class AnalyticsService extends AbstractMTGStockService {

	

	public List<HandyList> listHandyList()
	{
		String url=MTGStockConstants.MTGSTOCK_API_URI+"/lists";
		logger.debug("getting HandyList at {}",url);
		 List<HandyList> ret = new ArrayList<>();
		 
		 try {
			client.extractJson(url).getAsJsonArray().forEach(e->{
				var l = new HandyList();
				
				l.setId(e.getAsJsonObject().get(ID).getAsInt());
				l.setName(e.getAsJsonObject().get(NAME).getAsString());
				
				ret.add(l);
			});
		 }catch(Exception e)
		 {
			 logger.error("Error Getting handyList at {}",url);
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
		logger.debug("listing HandyList prints at {}",url);
		List<Print> ret = new ArrayList<>();
		
		 try {
				for(JsonElement e : client.extractJson(url).getAsJsonObject().get(PRINT+"s").getAsJsonArray())
				{
					ret.add(parsePrintFor(e.getAsJsonObject()));
				}
			 }catch(Exception e)
			 {
				 logger.error("Error Getting handyList at {}",url);
			 }
		
		return ret;
		
	}
	


	public List<LowHighValues> listAllTimes()
	{
		List<LowHighValues> ret = new ArrayList<>();
		
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/analytics/alltime";
		logger.debug("getting low/high value at {}",url);
		try {
			var arr = client.extractJson(url).getAsJsonArray();
			
			for(JsonElement el : arr)
			{
				var obj = el.getAsJsonObject();
				var val = new LowHighValues();
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
			var arr = client.extractJson(url).getAsJsonArray();
			
			
			for(JsonElement el : arr)
			{
				var p = new SetPrices();
				p.setSet(parseSetFor(el.getAsJsonObject().get(CARD_SET).getAsJsonObject()));
				var prices = el.getAsJsonObject().get("prices").getAsJsonObject();
				prices.keySet().stream().filter(s->!s.equalsIgnoreCase("num")).forEach(key->p.put(PRICES.valueOf(key.toUpperCase()), prices.get(key).getAsDouble()));
				
				if(prices.get("num")!=null)
					p.setNum(prices.get("num").getAsInt());
				
				setp.add(p);
			}
			
		} catch (IOException e) {
			logger.error("error getting expected value at {}",url, e);
		}
		
		
		return setp;
		
	}
	
	public List<Played> getMostPlayedCard(FORMAT f)
	{
		List<Played> ret = new ArrayList<>();
		int id=Tools.getFormatCode(f);
		
		var url = MTGStockConstants.MTGSTOCK_API_URI+"/analytics/mostplayed/"+id;
		
		logger.debug("get MostPlayedCard to {}",url);
		try {
			var arr = client.extractJson(url).getAsJsonObject().get(MOSTPLAYED).getAsJsonArray();
			
			arr.forEach(k->{
				var p = new Played();
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
			logger.error("Error getting mostplayedCard at {}",url,e);
		}
		return ret;
	}
	
	
	public List<Metagame> getMetagamesFor(FORMAT f)
	{
		List<Metagame> metas = new ArrayList<>();
		
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/analytics/metagame/"+Tools.getFormatCode(f);
		
		
		try {
			var arr = client.extractJson(url).getAsJsonArray();
			
			
			for(JsonElement e : arr)
			{
				var m = new Metagame();
						 m.setArchetype(parseArchetypeFor(e.getAsJsonObject().get(ARCHETYPE).getAsJsonObject()));
						 m.setDate(Tools.initDate(e.getAsJsonObject().get(DATE).getAsString(),DATE_FORMAT));
						 m.setFormat(f);
						 m.setTotal(e.getAsJsonObject().get(TOTAL).getAsInt());
						 if(e.getAsJsonObject().get(PLACING)!=null)
							 m.setPlacings(e.getAsJsonObject().get(PLACING).getAsInt());
						 
	
				metas.add(m);
			}
			
			
		} catch (IOException e) {
			logger.error("Error getting metagames analytics for {}",f,e);
		}
		
		
		
		return metas;
	}
	
	
}
