package org.api.mtgstock.services;

import java.io.IOException;

import org.api.mtgstock.modele.CardDetails;
import org.api.mtgstock.modele.CardSet;
import org.api.mtgstock.modele.EntryValue;
import org.api.mtgstock.modele.PriceHash;
import org.api.mtgstock.modele.PriceVariations;
import org.api.mtgstock.modele.Prices;
import org.api.mtgstock.modele.Print;
import org.api.mtgstock.modele.SetPricesAnalysis;
import org.api.mtgstock.tools.MTGStockConstants;
import org.api.mtgstock.tools.Tools;
import org.api.mtgstock.tools.URLTools;
import org.api.mtgstock.tools.MTGStockConstants.PRICES;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PriceService extends AbstractMTGStockService {
	


	public Prices getPricesFor(CardDetails p) throws IOException
	{
		return getPricesFor(p.getId());
	}
	
	public Prices getPricesFor(Print print) throws IOException
	{
		return getPricesFor(print.getId());
	}

	private Prices getPricesFor(Integer id) throws IOException
	{
		Prices p = new Prices();
		
		for(PRICES price : MTGStockConstants.PRICES.values())
			p.put(price, getPricesFor(id,price));
		
		return p;
	}
	
	public PriceVariations getPricesFor(Integer id,PRICES categ) throws IOException {
		
		String url =MTGStockConstants.MTGSTOCK_API_URI+"/prints/"+id+"/prices";
		JsonObject pricesPrint = URLTools.extractJson(url).getAsJsonObject();
		PriceVariations prices = new PriceVariations(categ);
		
		for (JsonElement el : pricesPrint.get(categ.name().toLowerCase()).getAsJsonArray()) {
			long timest = el.getAsJsonArray().get(0).getAsLong();
			double value = el.getAsJsonArray().get(1).getAsDouble();
			
	
			prices.put(Tools.initDate(timest), value);
		}
		
		return prices;
	}
	
	public SetPricesAnalysis getSetPricesAnalysis(CardSet s)
	{
		return getSetPricesAnalysis(s.getId());
	}
	
	public SetPricesAnalysis getSetPricesAnalysis(Integer i)
	{
		SetPricesAnalysis prices = new SetPricesAnalysis();
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/card_sets/"+i+"/ev";
		logger.debug("getting SetPricesAnalysis at " + url);
		
		try 
		{
			JsonObject obj = URLTools.extractJson(url).getAsJsonObject();
			for(PRICES p : new PRICES[]{PRICES.LOW, PRICES.AVG,PRICES.HIGH,PRICES.MARKET})
			{
				PriceVariations pv = new PriceVariations(p);
				obj.get(p.name().toLowerCase()).getAsJsonArray().forEach(e->pv.put(Tools.initDate(e.getAsJsonArray().get(0).getAsLong()), e.getAsJsonArray().get(1).getAsDouble()));
				prices.getPrices().put(p, pv);
			}
			
			prices.setCardSet(parseSetFor(obj.get(CARD_SET).getAsJsonObject()));
			
			
			for(MTGStockConstants.RARITY r : MTGStockConstants.RARITY.values())
				prices.getPriceHash().add(parsePriceHashFor(obj.get(PRICE_HASH).getAsJsonObject().get(r.name()).getAsJsonObject(),r));
			
			PriceHash phbooster = new PriceHash();
			
			if(obj.get(BOOSTER)!=null) {
				JsonObject obooster = obj.get(BOOSTER).getAsJsonObject();
						   phbooster.setNum(obooster.get(NUM).getAsInt());
						   obooster.keySet().forEach(k->phbooster.getAvg().add(new EntryValue<>(PRICES.valueOf(k.toUpperCase()), obooster.get(k).getAsDouble())));
			}
			prices.setBooster(phbooster);
			
		} catch (Exception e) {
			logger.error("Error getting SetPricesAnalysis for " + url,e);
		}
		
		return prices;
	}
	

	
}