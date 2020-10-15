package org.mtgstock.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mtgstock.modele.Interest;
import org.mtgstock.modele.Interests;
import org.mtgstock.tools.MTGStockConstants;
import org.mtgstock.tools.MTGStockConstants.CATEGORY;
import org.mtgstock.tools.Tools;
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
					   interests.setDate(new Date(interestJson.get(DATE).getAsLong()));	
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
					 t.setDate(new Date(obj.get(DATE).getAsLong()));
				     t.setInterestType(obj.get(INTEREST_TYPE).getAsString());
					 t.setPercentage(obj.get(PERCENTAGE).getAsDouble());
					 t.setPricePresent(obj.get(PRESENT_PRICE).getAsDouble());
					 t.setPricePast(obj.get(PAST_PRICE).getAsDouble());
					 t.setPrint(Tools.getPrintfor(obj.get(PRINT).getAsJsonObject()));
			 ret.add(t);
		}
		return ret;
	}


	
}
