package org.mtgstock.test;

import java.io.IOException;

import org.junit.Test;
import org.mtgstock.modele.FullPrint;
import org.mtgstock.modele.SearchResult;
import org.mtgstock.services.CardsService;

public class CardServiceTest {

	
	
	@Test
	public void testSearch()
	{
		CardsService ser = new CardsService();
		
		SearchResult o = ser.getBestResult("Liliana of the Veil");
		
			try {
				FullPrint p = ser.getCard(o);
				p.getSets().forEach(s->{
					System.out.println(p.getName()+"\t"+s.getSetName());
				});
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
