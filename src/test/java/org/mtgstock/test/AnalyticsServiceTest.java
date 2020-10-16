package org.mtgstock.test;

import org.junit.Test;
import org.mtgstock.services.AnalyticsService;
import org.mtgstock.tools.MTGStockConstants.FORMAT;

public class AnalyticsServiceTest {

	
	
	@Test
	public void testSetPrices()
	{
		AnalyticsService serv = new AnalyticsService();
		serv.getMetagamesFor(FORMAT.PIONEER);
	}
}
