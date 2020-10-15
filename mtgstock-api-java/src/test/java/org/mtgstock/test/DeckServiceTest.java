package org.mtgstock.test;

import org.junit.Test;
import org.mtgstock.services.DecksServices;
import org.mtgstock.tools.MTGStockConstants.FORMAT;

public class DeckServiceTest {

	
	
	@Test
	public void listTournaments()
	{
		DecksServices d = new DecksServices();
		//d.listTournaments(FORMAT.MODERN).forEach(System.out::println);
		//d.listArchetypes(FORMAT.VINTAGE).forEach(System.out::println);
		
		
		d.listDeckForTournament(22312).forEach(System.out::println);
	}
	
	
}
