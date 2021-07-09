package org.api.mtgstock.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.api.mtgstock.modele.CardSet;
import org.api.mtgstock.modele.EntryValue;
import org.api.mtgstock.modele.FullPrint;
import org.api.mtgstock.modele.Interest;
import org.api.mtgstock.modele.Print;
import org.api.mtgstock.modele.SealedProduct;
import org.api.mtgstock.modele.SearchResult;
import org.api.mtgstock.tools.MTGStockConstants;
import org.api.mtgstock.tools.Tools;
import org.api.mtgstock.tools.MTGStockConstants.PRICES;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class CardsService extends AbstractMTGStockService {

	List<CardSet> cacheSets;
	
	
	public CardsService() {
		cacheSets = new ArrayList<>();
	}

	public SearchResult getBestResult(String name) {
		return search(name).get(0);
		
	}


	public List<SearchResult> search(String name)
	{
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/search/autocomplete/"+name.replace(" ", "%20");
		List<SearchResult> ret = new ArrayList<>();
		
		try {
			var arr = client.extractJson(url).getAsJsonArray();
			for(JsonElement e : arr)
			{
				var oe = e.getAsJsonObject();
				ret.add(new SearchResult(oe.get(ID).getAsInt(), oe.get(NAME).getAsString()));
			}
		} catch (IOException e) {
			logger.error(e);
		}
		return ret;
	}
	
	public List<FullPrint> toPrints(List<SearchResult> res)
	{
		return toPrints(res.stream().map(SearchResult::getId).toArray(Integer[]::new));
	}
	
	public List<FullPrint> toPrints(Integer... ids)
	{
		
		List<FullPrint> ret = new ArrayList<>();

		for(Integer id : ids)
		{
			try {
				ret.add(getCard(id));
			} catch (IOException e) {
				logger.error("Error getting card for id " + id,e);
			}
		}
		
		return ret;
	}
	
	public FullPrint getCard(Interest sr) throws IOException
	{
		return getCard(sr.getPrint().getId());
	}
	

	public FullPrint getCard(SearchResult sr) throws IOException
	{
		return getCard(sr.getId());
	}
	
	public FullPrint getCard(Print p) throws IOException
	{
		return getCard(p.getId());
	}
		
	public List<CardSet> listSets()
	{
		if(!cacheSets.isEmpty())
			return cacheSets;
		
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/card_sets";
		logger.debug("getting sets at " + url);
		try {
			for(JsonElement e : client.extractJson(url).getAsJsonArray())
				cacheSets.add(parseSetFor(e.getAsJsonObject()));
		
		} catch (IOException e) {
			logger.error("Error gettings sets ",e);
			
		}
		
		for(CardSet cs : cacheSets)
		{
			CardSet extra = cacheSets.stream().filter(c->c.getName().equalsIgnoreCase(cs.getName() +": Extras")).findFirst().orElse(null);
			cs.setExtraSet(extra);
		}
			
		return cacheSets;
	}
	
	public List<CardSet> getSetByName(String name)
	{
		return listSets().stream().filter(s->s.getName().toLowerCase().startsWith(name.toLowerCase())).collect(Collectors.toList());
	}
	
	public CardSet getSetByCode(String id)
	{
		return listSets().stream().filter(s->id.equalsIgnoreCase(s.getAbbrevation())).findAny().orElse(null);
	}
	
	public CardSet getSetById(int id)
	{
		return listSets().stream().filter(s->s.getId()==id).findAny().orElse(null);
	}
	
	
	public List<Print> getPrintsBySet(CardSet set)
	{
		if(set.getExtraSet()==null)
			return getPrintsBySetId(set.getId());
		
	
		List<Print> ret = new ArrayList<>();
			ret.addAll(getPrintsBySetId(set.getId()));
			ret.addAll(getPrintsBySetId(set.getExtraSet().getId()));
			
		return ret;
	}
	

	public List<Print> getPrintsBySetCode(String abbresv)
	{
		Optional<CardSet> i = listSets().stream().filter(cs->abbresv.equalsIgnoreCase(cs.getAbbrevation())).findFirst();
		
		if(i.isPresent())
			return getPrintsBySet(i.get());
		else
			return new ArrayList<>();
	}
	
	private List<Print> getPrintsBySetId(Integer id)
	{
		
		List<Print> prints = new ArrayList<>();
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/card_sets/"+id;
		logger.debug("get prints at " + url);
		try {
			
			var arr = client.extractJson(url).getAsJsonObject().get(PRINT+"s").getAsJsonArray();
			
			for(JsonElement el : arr)
			{
				var p = parsePrintFor(el.getAsJsonObject());
				
				if(p.getSetId()==null) {
					CardSet st = getSetById(id);
					p.setSetId(st.getId());
					p.setSetName(st.getName());
					p.setSetType(st.getSetType());
				}
				prints.add(p);
			}
			
			
		} catch (IOException e) {
			logger.error("Error gettings prints for set="+id,e);
		} 
		
		
		
		return prints;
	}
	

	public List<SealedProduct> getSealedProduct(CardSet c)
	{
		return getSealedProduct(c.getId());
	}
	
	
	public List<SealedProduct> getSealedProduct(Integer id)
	{
		List<SealedProduct> ret = new ArrayList<>();
		String url =MTGStockConstants.MTGSTOCK_API_URI+"/card_sets/"+id+"/sealed";
		JsonArray  pricesPrint ;
		try {
				logger.debug("getting getSealedProduct  at " + url);
				pricesPrint = client.extractJson(url).getAsJsonArray();
			} catch (IOException e) {
					logger.error(e);
					return new ArrayList<>();
			}
		
			for(JsonElement e : pricesPrint)
			{
				
				var obj = e.getAsJsonObject();
				var p = new SealedProduct();
					  p.setId(obj.get("id").getAsInt());
					  p.setName(obj.get("name").getAsString());
					  p.setUrlImage(obj.get("image")!=null?obj.get("image").getAsString():null);
					  p.setSlug(obj.get("slug").getAsString());
						    obj.get("latestPrice").getAsJsonObject().entrySet().forEach(entry->{
						    	try {
						    	p.getLatestPrices().put(PRICES.valueOf(entry.getKey().toUpperCase()),entry.getValue().getAsDouble());
						    	}
						    	catch(UnsupportedOperationException ex)
						    	{
						    		logger.trace("error getting prices :"+ ex);
						    	}
						    });
								    
				ret.add(p);
			}
			
			return ret;
		
	}
	
	
	
	
	public FullPrint getCard(Integer id) throws IOException
	{
		
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/prints/"+id;
		
		logger.debug("read card at " + url);
		JsonObject o = client.extractJson(url).getAsJsonObject();
		
		FullPrint fp = new FullPrint();
				  fp.setId(o.get(ID).getAsInt());
				  fp.setName(o.get(NAME).getAsString());
				  fp.setNamePrecision(Tools.extractParenthesisValue(fp.getName()));
				  fp.setBorderless(o.get(NAME).getAsString().contains(MTGStockConstants.BORDERLESS));
				  fp.setExtendedArt(o.get(NAME).getAsString().contains(MTGStockConstants.EXTENDED_ART));
				  fp.setShowcase(o.get(NAME).getAsString().contains(MTGStockConstants.SHOWCASE));
				  fp.setOversized(o.get(NAME).getAsString().contains(MTGStockConstants.OVERSIZED));
				  fp.setFullArt(o.get(NAME).getAsString().contains(MTGStockConstants.FULL_ART));
				  fp.setEtched(o.get(NAME).getAsString().contains(MTGStockConstants.ETCHED));
				  fp.setJapanese(o.get(NAME).getAsString().contains(MTGStockConstants.JAPANESE));
				  
				  try {
				  fp.setRarity(MTGStockConstants.RARITY.valueOf(o.get(RARITY).getAsString()));
				  }
				  catch(Exception e)
				  {
					  logger.trace("Rarity "  + o.get(RARITY) + " for " + fp.getName() +" doesn't exist");
				  }
				  fp.setFoil(o.get(FOIL).getAsBoolean());
				  fp.setFlip(o.get(FLIP).getAsBoolean());
				  fp.setImageFlip(o.get(IMAGE_FLIP).getAsString());
				  fp.setImage(o.get(IMAGE).getAsString());
				  
				  if(o.get(MKM_ID)!=null && !o.get(MKM_ID).isJsonNull()) 
					  fp.setMkmId(o.get(MKM_ID).getAsInt());
				  
				  if(o.get(MKM_URL)!=null && !o.get(MKM_URL).isJsonNull()) 
					  fp.setMkmUrl(o.get(MKM_URL).getAsString());
				  
				  if(!o.get(TCG_ID).isJsonNull()) 
					  fp.setTcgId(o.get(TCG_ID).getAsInt());
				  
				  if(!o.get(TCG_URL).isJsonNull()) 
					  fp.setTcgUrl(o.get(TCG_URL).getAsString());
				  
				  fp.setCard(parseCardFor(o.get(CARD).getAsJsonObject()));
				  
				  fp.setCardSet(parseSetFor(o.get(CARD_SET).getAsJsonObject()));
				  
				  if(fp.getCardSet()!=null) {
					  fp.setSetId(fp.getCardSet().getId());
					  fp.setSetName(fp.getCardSet().getName());
					  fp.setSetType(fp.getCardSet().getSetType());
				  }
				  
				  fp.setAllTimeLow(new EntryValue<>(o.get(ALL_TIME_LOW).getAsJsonObject().get(AVG).getAsDouble(),Tools.initDate(o.get(ALL_TIME_LOW).getAsJsonObject().get(DATE).getAsLong())));
				  fp.setAllTimeHigh(new EntryValue<>(o.get(ALL_TIME_HIGH).getAsJsonObject().get(AVG).getAsDouble(),Tools.initDate(o.get(ALL_TIME_HIGH).getAsJsonObject().get(DATE).getAsLong())));
				  
				  if(!o.get(LATEST_PRICE_CK).getAsJsonObject().get(PRICE).isJsonNull())
					  fp.setLatestPriceCardKingdom(new EntryValue<>(o.get(LATEST_PRICE_CK).getAsJsonObject().get(PRICE).getAsDouble(),o.get(LATEST_PRICE_CK).getAsJsonObject().get(URL).getAsString()));
				  
				  if(!o.get(LATEST_PRICE_MKM).getAsJsonObject().get(AVG).isJsonNull())
					  fp.setLatestPriceMkm(new EntryValue<>(o.get(LATEST_PRICE_MKM).getAsJsonObject().get(AVG).getAsDouble(),o.get(LATEST_PRICE_MKM).getAsJsonObject().get(LOW).getAsDouble()));
				  
				  
				  if(o.get(LATEST_PRICE_MM)!=null && !o.get(LATEST_PRICE_MM).getAsJsonObject().get(PRICE).isJsonNull())
					  fp.setLatestPriceMiniatureMarket(new EntryValue<>(o.get(LATEST_PRICE_MM).getAsJsonObject().get(PRICE).getAsDouble(),o.get(LATEST_PRICE_MM).getAsJsonObject().get(URL).getAsString()));
				  
				  if(!o.get(MULTIVERSE_ID).isJsonNull())
					  fp.setMultiverseId(o.get(MULTIVERSE_ID).getAsInt());
				  
				  
				  o.get(LATEST_PRICE).getAsJsonObject().keySet().forEach(key->{
					  try {
						  fp.getLatestPrices().put(MTGStockConstants.PRICES.valueOf(key.toUpperCase()),o.get(LATEST_PRICE).getAsJsonObject().get(key).getAsDouble());
					  }
					  catch(Exception e)
					  {
						  //do nothing
					  }
				  });
				  
				  
					o.get(SETS).getAsJsonArray().forEach(je->{
							JsonObject obj = je.getAsJsonObject();
							fp.getSets().add(parsePrintFor(obj));
					});
					  
		return fp;
	}

	
	
	
	
}
