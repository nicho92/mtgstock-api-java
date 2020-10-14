package org.mtgstock.services;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.mtgstock.modele.Card;
import org.mtgstock.modele.HistoryPrice;
import org.mtgstock.modele.Prices;
import org.mtgstock.modele.Print;
import org.mtgstock.tools.MTGStockConstants;
import org.mtgstock.tools.MTGStockConstants.PRICES;
import org.mtgstock.tools.URLTools;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PriceService extends AbstractMTGStockService {
	
	public Prices getPricesFor(Card p) throws IOException
	{
		return getPricesFor(p.getId());
	}
	
	public Prices getPricesFor(Print p) throws IOException
	{
		return getPricesFor(p.getId());
	}

	private Prices getPricesFor(Integer id) throws IOException
	{
		Prices p = new Prices();
		
		for(PRICES price : MTGStockConstants.PRICES.values())
		{
			p.put(price, getPricesFor(id,price));
		}
		
		return p;
	}
	
	
	
	public HistoryPrice getPricesFor(Integer id,PRICES categ) throws IOException {
		
		String url =MTGStockConstants.MTGSTOCK_API_URI+"/prints/"+id+"/prices";
		JsonObject pricesPrint = URLTools.extractJson(url).getAsJsonObject();
		HistoryPrice prices = new HistoryPrice(categ);
		
		for (JsonElement el : pricesPrint.get(categ.name().toLowerCase()).getAsJsonArray()) {
			long timest = el.getAsJsonArray().get(0).getAsLong();
			double value = el.getAsJsonArray().get(1).getAsDouble();
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date(timest));
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
	
			prices.put(cal.getTime(), value);
		}
		
		return prices;
		
	}
	
	
}
