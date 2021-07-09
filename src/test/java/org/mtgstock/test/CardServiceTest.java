package org.mtgstock.test;

import java.util.Comparator;

import org.api.mtgstock.modele.CardSet;
import org.api.mtgstock.modele.FullPrint;
import org.api.mtgstock.modele.Print;
import org.api.mtgstock.modele.SearchResult;
import org.api.mtgstock.services.CardsService;
import org.api.mtgstock.services.InterestsService;
import org.api.mtgstock.services.PriceService;
import org.api.mtgstock.tools.MTGStockConstants.FORMAT;
import org.api.mtgstock.tools.MTGStockConstants.PRICES;
import org.junit.Test;

public class CardServiceTest {

	public void getOnlineShakerForFormat()
	{
		InterestsService serv = new InterestsService();
		serv.getInterestFor(PRICES.AVERAGE,true,FORMAT.VINTAGE).stream().forEach(i->{
			System.out.println(i.getPrint().getCleanName() +" " + i.getPrint().getSetName()+ " " + i.getPercentage()+"% " + i.getPricePresent()+"$" );
		});
	}

	
	public void getOnlineShakerForSet()
	{
		CardsService serv = new CardsService();
		
		serv.getPrintsBySetCode("KHM").stream().sorted(Comparator.comparing(Print::getName)).forEach(i->{
			System.out.println(i.getCleanName() + " " + i.isShowcase());
		});
		
		
	}
	
	public void listNoAbbrev()
	{
		CardsService serv = new CardsService();
		serv.listSets().stream().filter(cs->cs.getAbbrevation()==null).forEach(cs->{
			System.out.println(cs.getId() +" " + cs);
		});
	}
	
	
	public void listSet()
	{
		CardsService serv = new CardsService();

		serv.listSets().forEach(cs->{
			
			System.out.println(cs.getName() +" " + cs.getAbbrevation() + " extra=" + cs.getExtraSet());
			
		});
		
	}
	
	@Test
	public void getPrintsBySetCode()
	{
		
		String ed = "STA";
		CardsService serv = new CardsService();
		
		serv.getPrintsBySetCode(ed).forEach(p->{
			
			System.out.println(p.getNamePrecision());
			
		});
		
	}
	
	
	
	public void getPricesForEdition()
	{
		String ed = "STA";
		CardsService serv = new CardsService();

		serv.getPrintsBySetCode(ed).forEach(p->{
			
			System.out.println(p+ " " + p.getLastWeekPrice()+" " + p.getLastWeekPreviousPrice() + " " + p.getLatestPrices() + " " + p.isEtched());
			
		});
		
	}
	
	
	
	public void getOnlinePricesVariation()
	{
		String name = "Mavinda, Students' Advocate";
		String ed = "STX";
		PRICES p = PRICES.MARKET_FOIL;
		
		CardsService serv = new CardsService();
		SearchResult rs = serv.getBestResult(name);
		PriceService prices = new PriceService();
		
		try {
			FullPrint fp = serv.getCard(rs);
			CardSet set = serv.getSetByCode(ed);
			System.out.println("fp="+fp.getId() + " "+ fp + " " + fp.getCardSet().getName());
			Print fpSet = fp.getPrintForSetId(set.getId());
			prices.getPricesFor(fpSet).getPricesVariationsFor(p).forEach(System.out::println);
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
