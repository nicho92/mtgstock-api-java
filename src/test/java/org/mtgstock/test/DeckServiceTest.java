package org.mtgstock.test;

import org.api.mtgstock.modele.Deck;
import org.api.mtgstock.services.DecksServices;
import org.api.mtgstock.tools.MTGStockConstants.FORMAT;
import org.junit.Test;

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
