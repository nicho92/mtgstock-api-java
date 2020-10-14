package org.mtgstock.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mtgstock.modele.SearchResult;
import org.mtgstock.tools.MTGStockConstants;
import org.mtgstock.tools.URLTools;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class SearchService extends AbstractMTGStockService {
	public List<SearchResult> search(String name)
	{
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/search/autocomplete/"+name;
		List<SearchResult> ret = new ArrayList<>();
		
		try {
			JsonArray arr = URLTools.extractJson(url).getAsJsonArray();
			for(JsonElement e : arr)
			{
				JsonObject oe = e.getAsJsonObject();
				ret.add(new SearchResult(oe.get("id").getAsInt(), oe.get("name").getAsString(), oe.get("similarity").getAsDouble()));
			}
		} catch (IOException e) {
			logger.error(e);
		}
		
		
		return ret;
		
	}
	
	
}
