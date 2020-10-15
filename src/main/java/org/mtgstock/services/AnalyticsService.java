package org.mtgstock.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mtgstock.modele.Played;
import org.mtgstock.modele.Set;
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

	
	
	public SetPrices getExpectedValuesFor(Set s)
	{
		Optional<SetPrices> opt = getMostExpectedValue().stream().filter(sp->sp.getSet().getName().equals(s.getName())).findAny();
		
		if(opt.isPresent())
			return opt.get();
		else
			return null;
		
	}
	
	public List<SetPrices> getMostExpectedValue()
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
			e.printStackTrace();
			logger.error("error getting expected value at " + url, e);
		}
		
		
		return setp;
		
	}
	
	
	public List<Played> getMostPlayedCard(FORMAT f)
	{
		List<Played> ret = new ArrayList<>();
		int id=Tools.getFormatCode(f);
		
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/analytics/mostplayed/"+id;
		try {
			JsonArray arr = URLTools.extractJson(url).getAsJsonObject().get(MOSTPLAYED).getAsJsonArray();
			
			arr.forEach(k->{
				
				
				Played p = new Played();
				   	   p.setName(k.getAsJsonObject().get(CARD).getAsJsonObject().get(NAME).getAsString());
					   p.setId(k.getAsJsonObject().get(CARD).getAsJsonObject().get(PRINT).getAsJsonObject().get(ID).getAsInt());
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
	
}
