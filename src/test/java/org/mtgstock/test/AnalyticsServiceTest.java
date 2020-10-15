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
		
		System.out.println(serv.getExpectedValuesFor(pr.getSet()));
		
	}
}
