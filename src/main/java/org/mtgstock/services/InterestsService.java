package org.mtgstock.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.mtgstock.modele.Interest;
import org.mtgstock.modele.Interests;
import org.mtgstock.tools.MTGStockConstants;
import org.mtgstock.tools.MTGStockConstants.CATEGORY;
import org.mtgstock.tools.MTGStockConstants.FORMAT;
import org.mtgstock.tools.URLTools;

import com.google.gson.JsonObject;

public class InterestsService extends AbstractMTGStockService {
	
	private Interests interests;

	
	public void reload()
	{
		interests=null;
		interests=getInterests();
	}
	
	
	public List<Interest> getInterestFor(CATEGORY c , boolean foil,FORMAT f)
	{
		return getInterestFor(c , foil);
	}
	
	
	public List<Interest> getInterestFor(CATEGORY c , boolean foil)
	{
		
		if(c == CATEGORY.AVERAGE)
			return (foil)?getInterests().getAverageFoil():getInterests().getAverage();
		 
		
		return (foil)?getInterests().getMarketFoil():getInterests().getMarket();
		
	}
	
	
	public Interests getInterests()
	{
		
		if(interests!=null)
			return interests;
		
		
		String url=MTGStockConstants.MTGSTOCK_API_URI+"/interests";
		
		interests = new Interests();
		logger.debug("get Interests at " + url);
		
		try {
			JsonObject interestJson = URLTools.extractJson(url).getAsJsonObject();
					   interests.setDate(new Date(interestJson.get(DATE).getAsLong()));	
					   interests.setAverage(parseInterestFor(CATEGORY.AVERAGE, false,interestJson.get(CATEGORY.AVERAGE.name().toLowerCase()).getAsJsonObject()));
					   interests.setMarket(parseInterestFor(CATEGORY.MARKET, false,interestJson.get(CATEGORY.MARKET.name().toLowerCase()).getAsJsonObject()));
					   interests.setAverageFoil(parseInterestFor(CATEGORY.AVERAGE, true,interestJson.get(CATEGORY.AVERAGE.name().toLowerCase()).getAsJsonObject()));
					   interests.setMarketFoil(parseInterestFor(CATEGORY.MARKET, true,interestJson.get(CATEGORY.MARKET.name().toLowerCase()).getAsJsonObject()));
			
		} catch (IOException e) {
			logger.error("error getting interests at " + url, e);
		}
		return interests;
	}
	
	


	
}
