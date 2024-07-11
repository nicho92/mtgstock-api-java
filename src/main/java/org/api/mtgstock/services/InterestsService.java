package org.api.mtgstock.services;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.api.mtgstock.modele.Interest;
import org.api.mtgstock.modele.Interests;
import org.api.mtgstock.tools.MTGStockConstants;
import org.api.mtgstock.tools.MTGStockConstants.FORMAT;
import org.api.mtgstock.tools.MTGStockConstants.PRICES;

public class InterestsService extends AbstractMTGStockService {
	
	private Interests interests;

	
	public void reload()
	{
		interests=null;
		interests=getInterests();
	}
	
	
	public List<Interest> getInterestFor(PRICES c , boolean foil,FORMAT f)
	{
		return getInterestFor(c , foil).stream().filter(i->i.isLegalFor(f)).collect(Collectors.toList());
	}
	
	public List<Interest> getInterestFor(PRICES c ,FORMAT f)
	{
		return getInterestFor(c).stream().filter(i->i.isLegalFor(f)).collect(Collectors.toList());
	}
	
	
	public List<Interest> getInterestFor(PRICES c)
	{
		List<Interest> ret = new ArrayList<>();
		
		ret.addAll(getInterestFor(c, true));
		ret.addAll(getInterestFor(c, false));
		
		return ret;
	}
	
	
	
	public List<Interest> getInterestFor(PRICES c , boolean foil)
	{
		logger.debug("GetInterest for {} {} ",c,((foil)?"Foil":""));
		
		if(c == PRICES.AVERAGE)
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
			var interestJson = client.extractJson(urlAvg).getAsJsonObject();
			
			if(interestJson.get("error")!=null)
				throw new IOException(interestJson.get("error").getAsString());
	
			
						var je = interestJson.get(DATE);
						var d = new SimpleDateFormat(MTGStockConstants.DATE_FORMAT).parse(je.getAsString());
						
						
					   interests.setAverage(parseInterestFor(PRICES.AVERAGE, interestJson.get(PRICES.AVERAGE.name().toLowerCase()).getAsJsonObject().get(NORMAL).getAsJsonArray()));
					   interests.setAverageFoil(parseInterestFor(PRICES.AVERAGE, interestJson.get(PRICES.AVERAGE.name().toLowerCase()).getAsJsonObject().get(FOIL).getAsJsonArray()));
						
					   try {
						   interestJson = client.extractJson(urlMkt).getAsJsonObject();
						   interests.setMarket(parseInterestFor(PRICES.MARKET, interestJson.get(PRICES.MARKET.name().toLowerCase()).getAsJsonObject().get(NORMAL).getAsJsonArray()));
						   interests.setMarketFoil(parseInterestFor(PRICES.MARKET, interestJson.get(PRICES.MARKET.name().toLowerCase()).getAsJsonObject().get(FOIL).getAsJsonArray()));
					   }
					   catch(Exception e)
					   {
						   logger.error("No market data found");
					   }
					   interests.setDate(d);
					  logger.debug("Interests are stored in memory at date : {}",interests.getDate());		   
					   
		} catch (Exception e) {
			logger.error("error getting interests", e);
		}
		return interests;
	}
	
}
