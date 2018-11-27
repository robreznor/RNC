package cl.minsal.api.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Utils {

	public static int calculateAge(Date birthDate, Date currentDate) {            
	    // validate inputs ...                                                                               
	    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");                           
	    int d1 = Integer.parseInt(formatter.format(birthDate));                            
	    int d2 = Integer.parseInt(formatter.format(currentDate));                          
	    int age = (d2 - d1) / 10000;                                                       
	    return age;                                                                        
	}
	
	public void test() {
		try{
			Date current = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
	        String dateInString = "21-Nov-1986";
	        Date date = formatter.parse(dateInString);
			System.out.println(calculateAge(date, current));
		}catch(ParseException e){
			e.printStackTrace();
		}	
	}
}
