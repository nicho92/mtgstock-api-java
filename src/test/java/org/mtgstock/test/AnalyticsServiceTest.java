package org.mtgstock.test;

import org.junit.Test;
import org.mtgstock.services.AnalyticsService;

public class AnalyticsServiceTest {

	
	
	@Test
	public void testSetPrices()
	{
		AnalyticsService serv = new AnalyticsService();
		serv.listAllTimes().forEach(lwh->{
			
			System.out.println(lwh.getPrint() + " " + lwh.getType() + " " + lwh.getPrice());
		});
	}
}
