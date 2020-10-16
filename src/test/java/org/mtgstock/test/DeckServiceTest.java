package org.mtgstock.test;

import org.junit.Test;
import org.mtgstock.modele.Deck;
import org.mtgstock.services.DecksServices;
import org.mtgstock.tools.MTGStockConstants.FORMAT;

public class DeckServiceTest {

	
	
	@Test
	public void listTournaments()
	{
		DecksServices d = new DecksServices();
		d.listTournaments(FORMAT.MODERN).forEach(System.out::println);
		d.listArchetypes(FORMAT.VINTAGE).forEach(System.out::println);
		
		Deck deck = d.getDecksDetails(258088);
		System.out.println(deck.getMainboard());
		
	}
	
	
}
