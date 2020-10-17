package org.mtgstock.test;

import org.junit.Test;
import org.mtgstock.services.CardsService;

public class CardServiceTest {

	
	
	@Test
	public void testSearch()
	{
		CardsService ser = new CardsService();
		
		
		ser.getPrintBySet(68).forEach(s->{
			
			System.out.println(s.getCleanName() +" " + s.getLatestPrices());
			
		});
		
		
//		SearchResult o = ser.getBestResult("Liliana of the Veil");
//		
//			try {
//				FullPrint p = ser.getCard(o);
//				p.getSets().forEach(s->{
//					System.out.println(p.getName()+"\t"+s.getSetName());
//				});
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
	}
}
