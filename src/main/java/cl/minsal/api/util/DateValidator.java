package cl.minsal.api.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator {

	public static boolean isThisDateValid(String dateToValidate, String dateFromat){
		
		if(dateToValidate == null) return false;

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
		
		try {
			
			//if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
		
		} catch (ParseException e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static boolean isThisDateInRange(String stringDate, String dateFromat, String minDateString, String maxDateString){
		
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(stringDate);
			
			if(minDateString != null){
				Date minDate = sdf.parse(minDateString);
				if(!minDateIsValid(date, minDate)) return false;
			}
			if(maxDateString != null){
				Date maxDate = sdf.parse(maxDateString);
				if(!maxDateIsValid(date, maxDate)) return false;
			}
		
		} catch (ParseException e) {
			
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	private static boolean minDateIsValid(Date date, Date minDate){
		int d1 = Utils.dateToInteger(date, "yyyyMMdd");                           
		int d2 = Utils.dateToInteger(minDate, "yyyyMMdd");
		
		return d1>d2;
		
	}
	
	private static boolean maxDateIsValid(Date date, Date maxDate){
		return !minDateIsValid(date, maxDate);
	}
	
}
