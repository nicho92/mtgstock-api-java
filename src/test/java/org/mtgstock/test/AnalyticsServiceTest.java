package org.mtgstock.test;

import org.junit.Test;
import org.mtgstock.services.InterestsService;
import org.mtgstock.tools.MTGStockConstants.CATEGORY;
import org.mtgstock.tools.MTGStockConstants.FORMAT;

public class AnalyticsServiceTest {

	
	
	@Test
	public void testSetPrices()
	{
		InterestsService serv = new InterestsService();
		serv.getInterestFor(CATEGORY.MARKET,true, FORMAT.STANDARD).forEach(System.out::println);
		System.out.println("NON FOIL ");
		serv.getInterestFor(CATEGORY.MARKET,false, FORMAT.STANDARD).forEach(System.out::println);
	}
}
