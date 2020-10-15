package org.mtgstock.test;

import java.util.List;

import org.junit.Test;
import org.mtgstock.modele.SetPrices;
import org.mtgstock.services.AnalyticsService;

public class AnalyticsServiceTest {

	
	
	@Test
	public void testSetPrices()
	{
		AnalyticsService serv = new AnalyticsService();
		
		List<SetPrices> p = serv.getMostExpectedValue();
		
		
		SetPrices pr = p.get(4);
		
		serv.getExpectedValuesFor(pr.getSet()).getPrices().entrySet().forEach(System.out::println);
		
	}
}
