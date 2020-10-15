package org.mtgstock.tools;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MTGStockConstants {

	
	public static final String MTGSTOCK_API_URI ="https://api.mtgstocks.com";
	public static final String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36";
	public static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;
	public static final String OVERSIZED = "(Oversized)";
	public static final String EXTENDED_ART = "(Extended Art)";
	public static final String BORDERLESS = "(Borderless)";
	public static final String SHOWCASE="(Showcase)";
	
	public enum PRICES {LOW, AVG, HIGH, FOIL, MARKET, MARKET_FOIL}
	public enum CATEGORY {MARKET,AVERAGE}
	public enum FORMAT {LEGACY,STANDARD,VINTAGE,MODERN}
}
