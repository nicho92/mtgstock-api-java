package org.mtgstock.test;

import org.junit.Before;
import org.junit.Test;
import org.mtgstock.modele.Prices;
import org.mtgstock.services.AnalyticsService;
import org.mtgstock.services.InterestsService;
import org.mtgstock.services.PriceService;
import org.mtgstock.tools.MTGStockConstants.CATEGORY;
import org.mtgstock.tools.MTGStockConstants.FORMAT;

public class AnalyticsServiceTest {

	
	

	
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
	
	
	@Test
	public void getSetPricesAnalysis()
	{
		PriceService servP = new PriceService();
		
		servP.getSetPricesAnalysis(337).getPriceHash().forEach(ph->{
			System.out.println(ph.getRarity() + " "+  ph.getAvg());
		});
	}
	
	
}
