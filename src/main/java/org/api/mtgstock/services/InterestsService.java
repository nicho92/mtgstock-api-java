package org.api.mtgstock.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.api.mtgstock.modele.Interest;
import org.api.mtgstock.modele.Interests;
import org.api.mtgstock.tools.MTGStockConstants;
import org.api.mtgstock.tools.MTGStockConstants.CATEGORY;
import org.api.mtgstock.tools.MTGStockConstants.FORMAT;

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
		return getInterestFor(c , foil).stream().filter(i->i.isLegalFor(f)).collect(Collectors.toList());
	}
	
	public List<Interest> getInterestFor(CATEGORY c ,FORMAT f)
	{
		return getInterestFor(c).stream().filter(i->i.isLegalFor(f)).collect(Collectors.toList());
	}
	
	
	public List<Interest> getInterestFor(CATEGORY c)
	{
		List<Interest> ret = new ArrayList<>();
		
		ret.addAll(getInterestFor(c, true));
		ret.addAll(getInterestFor(c, false));
		
		return ret;
	}
	
	
	
	public List<Interest> getInterestFor(CATEGORY c , boolean foil)
	{
		logger.debug("GetInterest for " + c + " " + ((foil)?"Foil":""));
		
		if(c == CATEGORY.AVERAGE)
			return (foil)?getInterests().getAverageFoil():getInterests().getAverage();
		 
		
		return (foil)?getInterests().getMarketFoil():getInterests().getMarket();
		
	}
	
	public Interests getInterests()
	{
		
		if(interests!=null)
			return interests;
		
		interests = new Interests();
		
		String urlAvg=MTGStockConstants.MTGSTOCK_API_URI+"/interests/average";
		String urlMkt=MTGStockConstants.MTGSTOCK_API_URI+"/interests/market";
		
		
		try {
			logger.debug("init connection");
			client.doGet(MTGStockConstants.MTGSTOCK_WEBSITE_URI);
			logger.debug("init connection done");
		} catch (IOException e1) {
			logger.error(e1);
			return interests;
		}
		
		try {
			JsonObject interestJson = client.extractJson(urlAvg).getAsJsonObject();
					   interests.setDate(new Date(interestJson.get(DATE).getAsLong()));
					  
					   interests.setAverage(parseInterestFor(CATEGORY.AVERAGE, interestJson.get(CATEGORY.AVERAGE.name().toLowerCase()).getAsJsonObject().get(NORMAL).getAsJsonArray()));
					   interests.setAverageFoil(parseInterestFor(CATEGORY.AVERAGE, interestJson.get(CATEGORY.AVERAGE.name().toLowerCase()).getAsJsonObject().get(FOIL).getAsJsonArray()));
						
			interestJson = client.extractJson(urlMkt).getAsJsonObject();
					   interests.setMarket(parseInterestFor(CATEGORY.MARKET, interestJson.get(CATEGORY.MARKET.name().toLowerCase()).getAsJsonObject().get(NORMAL).getAsJsonArray()));
					   interests.setMarketFoil(parseInterestFor(CATEGORY.MARKET, interestJson.get(CATEGORY.MARKET.name().toLowerCase()).getAsJsonObject().get(FOIL).getAsJsonArray()));
			
					   
			logger.debug("Interests are stored in memory at date : " + interests.getDate());		   
					   
		} catch (Exception e) {
			logger.error("error getting interests", e);
		}
		return interests;
	}
	
}
