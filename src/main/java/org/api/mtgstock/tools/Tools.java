package org.api.mtgstock.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import org.api.mtgstock.tools.MTGStockConstants.FORMAT;

public class Tools {

	private Tools() {
		
	}
	
	public static int getFormatCode(FORMAT f)
	{
		switch (f) 
		{
			case LEGACY:return 1;
			case MODERN:return 3;
			case STANDARD:return 4;
			case VINTAGE:return 2;
			case PIONEER:return 15;
			case PAUPER : return 7;
			case FRONTIER: return -1;
			case COMMANDER : return -1;
		}
		
		return -1;
	}
	
	
	public static Date initDate(Long d)
	{
		var cal = Calendar.getInstance();
			cal.setTime(new Date(d));
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}
	
	public static String extractParenthesisValue(String content)
	{
		var m = Pattern.compile("\\((.*?)\\)").matcher(content);
		if(m.find()) {
		    return m.group(1);
		}
		return "";
	}


	public static Date initDate(String val, String patt) {
		try {
			return new SimpleDateFormat(patt).parse(val);
		} catch (ParseException e) {
			return new Date();
		}
	}

	
	
	
}
