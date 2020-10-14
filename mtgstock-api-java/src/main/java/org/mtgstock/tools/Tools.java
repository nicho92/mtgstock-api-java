package org.mtgstock.tools;

import java.util.Calendar;
import java.util.Date;

public class Tools {

	
	
	public static Date initDate(Long d)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(d));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		
		return cal.getTime();
	}
}
