package org.api.mtgstock.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.api.mtgstock.modele.Interest;
import org.api.mtgstock.modele.Interests;
import org.api.mtgstock.tools.MTGStockConstants;
import org.api.mtgstock.tools.URLUtilities;
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
		logger.debug("GetInterest for " + c + " foil="+foil);
		
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
			JsonObject interestJson = URLUtilities.extractJson(url).getAsJsonObject();
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
