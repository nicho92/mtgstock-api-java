package org.mtgstock.test;

import org.junit.Test;
import org.mtgstock.modele.Interests;
import org.mtgstock.services.AnalyticsService;
import org.mtgstock.services.InterestsService;

public class AnalyticsServiceTest {

	
	
	@Test
	public void testSetPrices()
	{
		InterestsService serv = new InterestsService();
		serv.getInterests().getAverage().forEach(lwh->{
			
			System.out.println(lwh.getPrint().getLegal()+ " " + lwh.getPrint() + " " + lwh.getCategory() + " " + lwh.getPricePresent());
		});
	}
}
