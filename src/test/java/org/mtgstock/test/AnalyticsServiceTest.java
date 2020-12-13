package org.mtgstock.test;

import org.api.mtgstock.services.AnalyticsService;
import org.api.mtgstock.services.InterestsService;
import org.api.mtgstock.services.PriceService;
import org.api.mtgstock.tools.MTGStockConstants.CATEGORY;
import org.api.mtgstock.tools.MTGStockConstants.FORMAT;
import org.junit.Test;

public class AnalyticsServiceTest {

	
	

	@Test
	public void testSetPrices()
	{
		InterestsService servI = new InterestsService();
		servI.getInterestFor(CATEGORY.MARKET,true, FORMAT.STANDARD).forEach(System.out::println);
		servI.getInterestFor(CATEGORY.MARKET,false, FORMAT.STANDARD).forEach(System.out::println);
	}
	

	public void testHandyList()
	{
		AnalyticsService servA = new AnalyticsService();
		servA.listPrintsForHandyList(7).forEach(System.out::println);
	}
	
	
	public void testBestCards()
	{
		AnalyticsService servA = new AnalyticsService();
		servA.getMostPlayedCard(FORMAT.STANDARD).forEach(System.out::println);
	}
	
	
	
	public void getSetPricesAnalysis()
	{
		PriceService servP = new PriceService();
		
		servP.getSetPricesAnalysis(337).getPriceHash().forEach(ph->{
			System.out.println(ph.getRarity() + " "+  ph.getAvg());
		});
	}
	
	
}
