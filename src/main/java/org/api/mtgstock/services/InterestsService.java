package org.api.mtgstock.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.api.mtgstock.modele.Interest;
import org.api.mtgstock.modele.Interests;
import org.api.mtgstock.tools.MTGStockConstants;
import org.api.mtgstock.tools.MTGStockConstants.FORMAT;
import org.api.mtgstock.tools.MTGStockConstants.INTEREST;
import org.api.mtgstock.tools.MTGStockConstants.PRICES;

public class InterestsService extends AbstractMTGStockService {
	
	private Interests interests;

	
	public void reload()
	{
		interests=null;
		interests=getInterests();
	}
	
	
	public List<Interest> getInterestFor(INTEREST c , boolean foil,FORMAT f)
	{
		return getInterestFor(c , foil).stream().filter(i->i.isLegalFor(f)).collect(Collectors.toList());
	}
	
	public List<Interest> getInterestFor(INTEREST c ,FORMAT f)
	{
		return getInterestFor(c).stream().filter(i->i.isLegalFor(f)).collect(Collectors.toList());
	}
	
	
	public List<Interest> getInterestFor(INTEREST c)
	{
		List<Interest> ret = new ArrayList<>();
		
		ret.addAll(getInterestFor(c, true));
		ret.addAll(getInterestFor(c, false));
		
		return ret;
	}
	
	
	
	public List<Interest> getInterestFor(INTEREST c , boolean foil)
	{
		logger.debug("GetInterest for {} {} ",c,((foil)?"Foil":""));
		
		if(c == INTEREST.AVERAGE)
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
		String urlAvgFoil=MTGStockConstants.MTGSTOCK_API_URI+"/interests/average/foil";
		String urlMktFoil=MTGStockConstants.MTGSTOCK_API_URI+"/interests/market/foil";
						
		try {
				var obj = client.extractJson(urlAvg).getAsJsonObject();
				
				 		interests.setDate(new SimpleDateFormat(MTGStockConstants.DATE_FORMAT).parse(obj.get("date").getAsString()));
						interests.setAverage(parseInterestFor(INTEREST.AVERAGE, obj.get(INTERESTS).getAsJsonArray()));
						interests.setAverage(parseInterestFor(INTEREST.AVERAGE, client.extractJson(urlAvgFoil).getAsJsonObject().get(INTERESTS).getAsJsonArray()));
						interests.setMarket(parseInterestFor(INTEREST.MARKET, client.extractJson(urlMkt).getAsJsonObject().get(INTERESTS).getAsJsonArray()));
						interests.setMarketFoil(parseInterestFor(INTEREST.MARKET, client.extractJson(urlMktFoil).getAsJsonObject().get(INTERESTS).getAsJsonArray()));
						
					  logger.debug("Interests are stored in memory at date : {}",interests.getDate());		   
					   
		} catch (Exception e) {
			logger.error("error getting interests", e);
		}
		return interests;
	}
	
}
