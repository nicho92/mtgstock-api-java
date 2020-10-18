package org.mtgstock.test;

import java.io.IOException;

import org.junit.Test;
import org.mtgstock.modele.CardSet;
import org.mtgstock.modele.FullPrint;
import org.mtgstock.modele.Print;
import org.mtgstock.modele.SearchResult;
import org.mtgstock.services.CardsService;
import org.mtgstock.services.InterestsService;
import org.mtgstock.services.PriceService;
import org.mtgstock.tools.MTGStockConstants.CATEGORY;
import org.mtgstock.tools.MTGStockConstants.FORMAT;
import org.mtgstock.tools.MTGStockConstants.PRICES;

public class CardServiceTest {

	
	public void getOnlineShakerForFormat()
	{
		InterestsService serv = new InterestsService();
		
		
		serv.getInterestFor(CATEGORY.AVERAGE,false,FORMAT.STANDARD).forEach(i->{
			System.out.println(i.getPrint().getCleanName() +" " + i.getPrint().getSetName()+ " " + i.getPercentage()+"% " + i.getPricePresent()+"$" );
		});
	}

	
	@Test
	public void getPricesForEditiion()
	{
		String ed = "UMA";
		CardsService serv = new CardsService();

		serv.getPrintsByCode(ed).forEach(p->{
			
			System.out.println(p+ " " + p.getLastWeekPrice()+" " + p.getLastWeekPreviousPrice() + " " + p.getLatestPrices());
			
		});
		
	}
	
	
	
	public void getOnlinePricesVariation()
	{
		String name = "liliana of the veil";
		String ed = "UMA";
		PRICES p = PRICES.MARKET_FOIL;
		
		CardsService serv = new CardsService();
		SearchResult rs = serv.getBestResult(name);
		PriceService prices = new PriceService();
		
		try {
			FullPrint fp = serv.getCard(rs);
			CardSet set = serv.getSetByCode(ed);
			
			System.out.println("found " + set +" for " + name);
			Print fpSet = fp.getPrintForSetId(set.getId());
			
			prices.getPricesFor(fpSet).getPricesVariationsFor(p).forEach(System.out::println);
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
