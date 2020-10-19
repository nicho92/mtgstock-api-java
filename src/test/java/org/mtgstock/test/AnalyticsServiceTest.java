package org.mtgstock.test;

import org.junit.Test;
import org.mtgstock.services.AnalyticsService;
import org.mtgstock.services.InterestsService;
import org.mtgstock.tools.MTGStockConstants.CATEGORY;
import org.mtgstock.tools.MTGStockConstants.FORMAT;

public class AnalyticsServiceTest {

	
	
	
	public void testSetPrices()
	{
		InterestsService serv = new InterestsService();
		serv.getInterestFor(CATEGORY.MARKET,true, FORMAT.STANDARD).forEach(System.out::println);
		System.out.println("NON FOIL ");
		serv.getInterestFor(CATEGORY.MARKET,false, FORMAT.STANDARD).forEach(System.out::println);
	}
	
	@Test
	public void testHandyList()
	{
		AnalyticsService serv = new AnalyticsService();
		
		serv.listPrintsForHandyList(7).forEach(System.out::println);
	}
	
}
