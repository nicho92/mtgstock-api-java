package org.mtgstock.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mtgstock.modele.Interest;
import org.mtgstock.modele.Interests;
import org.mtgstock.modele.Legality;
import org.mtgstock.modele.Print;
import org.mtgstock.tools.MTGStockConstants;
import org.mtgstock.tools.MTGStockConstants.CATEGORY;
import org.mtgstock.tools.URLTools;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class InterestsService extends AbstractMTGStockService {
	
	public Interests getInterests()
	{
		String url=MTGStockConstants.MTGSTOCK_API_URI+"/interests";
		
		Interests interests = new Interests();
		logger.debug("get Interests at " + url);
		
		try {
			JsonObject interestJson = URLTools.extractJson(url).getAsJsonObject();
					   interests.setDate(new Date(interestJson.get("date").getAsLong()));	
					   interests.setAverage(getInterestFor(CATEGORY.AVERAGE, false,interestJson.get(CATEGORY.AVERAGE.name().toLowerCase()).getAsJsonObject()));
					   interests.setMarket(getInterestFor(CATEGORY.MARKET, false,interestJson.get(CATEGORY.MARKET.name().toLowerCase()).getAsJsonObject()));
					   interests.setAverageFoil(getInterestFor(CATEGORY.AVERAGE, true,interestJson.get(CATEGORY.AVERAGE.name().toLowerCase()).getAsJsonObject()));
					   interests.setMarketFoil(getInterestFor(CATEGORY.MARKET, true,interestJson.get(CATEGORY.MARKET.name().toLowerCase()).getAsJsonObject()));
			
		} catch (IOException e) {
			logger.error("error getting interests at " + url, e);
		}
		return interests;
	}
	
	private List<Interest> getInterestFor(CATEGORY c,boolean foil,JsonObject interests)
	{
		JsonArray arrs = interests.get( (foil)?"foil":"normal").getAsJsonArray(); 
		List<Interest> ret = new ArrayList<>();
		
		for(JsonElement e : arrs)
		{
			JsonObject obj = e.getAsJsonObject();
			Interest t = new Interest();
					 t.setCategory(c);
					 t.setFoil(foil);
					 t.setDate(new Date(obj.get("date").getAsLong()));
				     t.setInterestType(obj.get("interest_type").getAsString());
					 t.setPercentage(obj.get("percentage").getAsDouble());
					 t.setPricePresent(obj.get("present_price").getAsDouble());
					 t.setPricePast(obj.get("past_price").getAsDouble());
					 t.setPrint(getPrintfor(obj.get("print").getAsJsonObject()));
			 ret.add(t);
		}
		return ret;
	}


	private Print getPrintfor(JsonObject obj) {
		Print p = new Print();
			  p.setId(obj.get("id").getAsInt());
			  p.setName(obj.get("name").getAsString());
			  p.setIconClass(obj.get("icon_class").getAsString());
			  p.setRarity(obj.get("rarity").getAsString());
			  p.setReserved(obj.get("reserved").getAsBoolean());
			  p.setSetId(obj.get("set_id").getAsInt());
			  p.setSetName(obj.get("set_name").getAsString());
			  p.setSetType(obj.get("set_type").getAsString());
			  p.setIncludeDefault(obj.get("include_default").getAsBoolean());
			  p.setExtendedArt(obj.get("name").getAsString().contains(MTGStockConstants.EXTENDED_ART));
			  p.setOversized(obj.get("name").getAsString().contains(MTGStockConstants.OVERSIZED));
			  p.setBorderless(obj.get("name").getAsString().contains(MTGStockConstants.BORDERLESS));
			  p.setShowcase(obj.get("name").getAsString().contains(MTGStockConstants.SHOWCASE));
			  
			  for(String key : obj.get("legal").getAsJsonObject().keySet())
				  p.getLegal().add(new Legality(key, obj.get("legal").getAsJsonObject().get(key).getAsString()));
			  
			  
			  
			  try {
				p.setImage(obj.get("image").getAsString());
				} catch (Exception e) {
					//do nothing
				}
			 
		
		return p;
	}
}
