package org.api.mtgstock.services;

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
		
		String urlAvg=MTGStockConstants.MTGSTOCK_API_URI+"/interests/average/regular";
		String urlMkt=MTGStockConstants.MTGSTOCK_API_URI+"/interests/market/regular";
		String urlAvgFoil=MTGStockConstants.MTGSTOCK_API_URI+"/interests/market/foil";
		String urlMktFoil=MTGStockConstants.MTGSTOCK_API_URI+"/interests/market/foil";
						
		try {
				var obj = client.extractJson(urlAvg).getAsJsonObject();
				
				 		interests.setDate(new SimpleDateFormat(MTGStockConstants.DATE_FORMAT).parse(obj.get("date").getAsString()));
						interests.setAverage(parseInterestFor(PRICES.AVERAGE, obj.get("interests").getAsJsonArray()));
						interests.setAverage(parseInterestFor(PRICES.AVERAGE, client.extractJson(urlAvgFoil).getAsJsonObject().get("interests").getAsJsonArray()));
						interests.setMarket(parseInterestFor(PRICES.MARKET, client.extractJson(urlMkt).getAsJsonObject().get("interests").getAsJsonArray()));
						interests.setMarketFoil(parseInterestFor(PRICES.MARKET, client.extractJson(urlMktFoil).getAsJsonObject().get("interests").getAsJsonArray()));
						
					  logger.debug("Interests are stored in memory at date : {}",interests.getDate());		   
					   
		} catch (Exception e) {
			logger.error("error getting interests", e);
		}
		return interests;
	}
	
}
