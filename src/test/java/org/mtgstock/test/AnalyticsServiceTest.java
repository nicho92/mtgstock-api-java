package org.mtgstock.test;

import org.api.mtgstock.services.AnalyticsService;
import org.api.mtgstock.services.InterestsService;
import org.api.mtgstock.services.PriceService;
import org.api.mtgstock.tools.MTGStockConstants.FORMAT;
import org.api.mtgstock.tools.MTGStockConstants.PRICES;
import org.junit.Test;

public class AnalyticsServiceTest {

	
	

	@Test
	public void testSetPrices()
	{
		InterestsService servI = new InterestsService();
		servI.getInterestFor(PRICES.AVG,FORMAT.STANDARD).forEach(i->{
			System.out.println(i.getPrint() +" " + i.isFoil() + " ");
		});
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
