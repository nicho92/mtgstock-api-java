package org.api.mtgstock.tools;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MTGStockConstants {

	
	public static final String MTGSTOCK_API_URI ="https://api.mtgstocks.com";
	public static final String MTGSTOCK_WEBSITE_URI="https://www.mtgstocks.com";
	public static final String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36";
	public static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;
	public static final String OVERSIZED = "(Oversized)";
	public static final String EXTENDED_ART = "(Extended Art)";
	public static final String BORDERLESS = "(Borderless)";
	public static final String SHOWCASE="(Showcase)";
	public static final String FULL_ART="- Full Art";
	public static final String ETCHED="Foil Etched";
	public static final String JAPANESE="JP Alternate Art";
	public static final String VERSION = "1.1.4";
	public static final String DATE_FORMAT="yyyy-MM-dd";
	
	
	public enum INTEREST{MARKET,AVERAGE}
	public enum PRICES {LOW,AVG,HIGH,FOIL,MARKET,MARKET_FOIL}
	public enum FORMAT {LEGACY,STANDARD,VINTAGE,MODERN,PIONEER,PAUPER,FRONTIER,COMMANDER,DUEL,OLDSCHOOL,GLADIATOR,PENNY,PREMODERN,HISTORICBRAWL,PAUPERCOMMANDER,FUTURE,BRAWL,ALCHEMY,HISTORIC,EXPLORER,PREDH,OATHBREAKER,TIMELESS,STANDARDBRAWL}
	public enum RARITY {M,R,U,C}
}
