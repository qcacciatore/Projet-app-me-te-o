package pack;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.*;

public class Convert {
	
	public static void getTime(long timeStamp){
		Date date = new Date(timeStamp*1000L); // *1000 is to convert seconds to milliseconds
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM HH:mm:ss :"); // the format of your date
		//sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
		String formattedDate = sdf.format(date);
		System.out.println(formattedDate);
		
	}
	
	public static double getTempCelsius(double faren){
		double tempCelsius;
		tempCelsius = ((faren-32)*5)/9;
		tempCelsius = round(tempCelsius, 2);
		return tempCelsius;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public static double getPresPa(double mili){
		double pa;
		pa = mili * 100;
		return pa;
	}

}

