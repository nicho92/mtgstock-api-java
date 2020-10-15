package org.mtgstock.tools;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mtgstock.modele.CardDetails;
import org.mtgstock.modele.Legality;
import org.mtgstock.modele.Print;
import org.mtgstock.modele.Set;

import com.google.gson.JsonObject;

public class Tools {

	
	
	public static Date initDate(Long d)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(d));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		
		return cal.getTime();
	}
	
	public static String extractParenthesisValue(String content)
	{
		Matcher m = Pattern.compile("\\((.*?)\\)").matcher(content);
		if(m.find()) {
		    return m.group(1);
		}
		return "";
	}

	public static Print getPrintfor(JsonObject obj) {
		Print p = new Print();
			  p.setId(obj.get("id").getAsInt());
			  p.setName(obj.get("name").getAsString());
			  p.setIconClass(obj.get("icon_class").getAsString());
			  p.setRarity(obj.get("rarity").getAsString());
			
			  if(obj.get("reserved")!=null)
				  p.setReserved(obj.get("reserved").getAsBoolean());
			 
			  p.setSetId(obj.get("set_id").getAsInt());
			  p.setSetName(obj.get("set_name").getAsString());
			  
			  
			  if(obj.get("set_type")!=null)
				  p.setSetType(obj.get("set_type").getAsString());
			  
			  if(obj.get("include_default")!=null)
					 p.setIncludeDefault(obj.get("include_default").getAsBoolean());
			
			  p.setExtendedArt(obj.get("name").getAsString().contains(MTGStockConstants.EXTENDED_ART));
			  p.setOversized(obj.get("name").getAsString().contains(MTGStockConstants.OVERSIZED));
			  p.setBorderless(obj.get("name").getAsString().contains(MTGStockConstants.BORDERLESS));
			  p.setShowcase(obj.get("name").getAsString().contains(MTGStockConstants.SHOWCASE));
			  
			 
			  try {
				  
				  for(String key : obj.get("legal").getAsJsonObject().keySet())
					  p.getLegal().add(new Legality(key, obj.get("legal").getAsJsonObject().get(key).getAsString()));
			  
			  }
			  catch(Exception e)
			  {
				  
			  }
			  
			  try {
				p.setImage(obj.get("image").getAsString());
				} catch (Exception e) {
					//do nothing
				}
			 
		
		return p;
	}
	
	public static Set parseSetFor(JsonObject o) {
		Set set = new Set();
			set.setId(o.get("id").getAsInt());
			set.setName(o.get("name").getAsString());
			set.setIconClass(o.get("icon_class").getAsString());
			set.setSetType(o.get("set_type").getAsString());
			
			try {
				set.setAbbrevation(o.get("abbreviation").getAsString());
			}
			catch(Exception e)
			{
				//do nothing
			}
			
		
		
		return set;
	}


	public static CardDetails parseCardFor(JsonObject o) {
		
		CardDetails c = new CardDetails();
			 c.setId(o.get("id").getAsInt());
			 c.setCmc(o.get("cmc").getAsInt());
			 c.setCost(o.get("cost").getAsString());
			 o.get("legal").getAsJsonObject().entrySet().forEach(e->c.getLegal().add(new Legality(e.getKey(), e.getValue().getAsString())));
			 c.setLowestPrint(o.get("lowest_print").getAsInt());
			 c.setName(o.get("name").getAsString());
			 c.setOracle(o.get("oracle").getAsString());
			 c.setPwrtgh(o.get("pwrtgh").getAsString());
			 c.setReserved(o.get("reserved").getAsBoolean());
			 c.setSubtype(o.get("subtype").getAsString());
			 c.setSupertype(o.get("supertype").getAsString());
			 
		return null;
	}
	
	
	
}
