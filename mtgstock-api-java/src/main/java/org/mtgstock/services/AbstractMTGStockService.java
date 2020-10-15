package org.mtgstock.services;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class AbstractMTGStockService {
	

	protected static final String AVG = "avg";
	protected static final String NAME = "name";
	protected static final String QUANTITY = "quantity";
	protected static final String CARD = "card";
	protected static final String PRINT = "print";
	protected static final String LATEST_PRICE = "latest_price";
	protected static final String IMAGE = "image";
	protected static final String ID = "id";
	protected static final String PAST_PRICE = "past_price";
	protected static final String PRESENT_PRICE = "present_price";
	protected static final String PERCENTAGE = "percentage";
	protected static final String INTEREST_TYPE = "interest_type";
	protected static final String DATE = "date";
	protected static final String URL = "url";
	protected static final String LATEST_PRICE_MM = "latest_price_mm";
	protected static final String LATEST_PRICE_MKM = "latest_price_mkm";
	protected static final String LATEST_PRICE_CK = "latest_price_ck";
	protected static final String PRICE = "price";
	protected static final String ALL_TIME_HIGH = "all_time_high";
	protected static final String ALL_TIME_LOW = "all_time_low";
	protected static final String CARD_SET = "card_set";
	protected static final String TCG_URL = "tcg_url";
	protected static final String TCG_ID = "tcg_id";
	protected static final String MKM_URL = "mkm_url";
	protected static final String MKM_ID = "mkm_id";
	protected static final String IMAGE_FLIP = "image_flip";
	protected static final String FLIP = "flip";
	protected static final String FOIL = "foil";
	protected static final String RARITY = "rarity";
	protected static final String SETS = "sets";
	protected static final String MULTIVERSE_ID = "multiverse_id";
	protected static final String SIMILARITY = "similarity";
	protected static final String MOSTPLAYED = "mostplayed";
	protected static final String LOW = "low";

	protected Logger logger = LogManager.getLogger(this.getClass());

}
