package org.api.mtgstock.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.api.mtgstock.modele.News;
import org.api.mtgstock.tools.MTGStockConstants;

public class NewsService extends AbstractMTGStockService {

	
	
	public static void main(String[] args) {
		new NewsService().listNews().stream().map(n->n.toUri()).forEach(System.out::println);
	}
	
	
	public List<News> listNews()
	{
		
		var list = new ArrayList<News>();
		
		String url=MTGStockConstants.MTGSTOCK_API_URI+"/news";
		
		 try {
			client.extractJson(url).getAsJsonArray().forEach(e->{
				var jItem = e.getAsJsonObject();
				var news = new News();
				news.setId(jItem.get("id").getAsInt());
				news.setTitle(jItem.get("title").getAsString());
				news.setSlug(jItem.get("slug").getAsString());
				news.setDate(new Date(jItem.get("date").getAsLong()));
				news.setDescription(jItem.get("excerpt").getAsString());
				news.setCategoryId(jItem.get("category").getAsJsonObject().get("id").getAsInt());
				news.setCategoryLabel(jItem.get("category").getAsJsonObject().get("name").getAsString());
				
				
				
				
				list.add(news);
				
				
				
			});
		 }catch(Exception e)
		 {
			 logger.error("Error Getting news at " + url);
		 }
		 
		 return list;
		 
	}
	
}
