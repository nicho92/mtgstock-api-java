package org.mtgstock.test;

import org.junit.Test;
import org.mtgstock.services.CardsService;

public class CardServiceTest {

	
	
	@Test
	public void testSearch()
	{
		CardsService ser = new CardsService();
		
		
		ser.getPrintsByCode("JMP").forEach(s->{
			
			System.out.println(s.getCleanName() +" " + s.getLatestPrices());
			
		});
		
		
	}
}
