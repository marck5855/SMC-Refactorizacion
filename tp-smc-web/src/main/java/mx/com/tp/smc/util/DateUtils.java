package mx.com.tp.smc.util;

import org.joda.time.DateTime;

public class DateUtils {

	public static String getMonth() {
		DateTime date = new DateTime();
		int number = date.getMonthOfYear();
		String month = "";
		switch(number) {
			case 1:
				month = "Enero";
			break;
			
			case 2:
				month = "Febrero";
			break;
			
			case 3:
				month = "Marzo";
			break;
			
			case 4:
				month = "Abril";
			break;
			
			case 5:
				month = "Mayo";
			break;

			case 6:
				month = "Junio";
			break;
			
			case 7:
				month = "Julio";
			break;
			
			case 8:
				month = "Agosto";
			break;
			
			case 9:
				month = "Septiembre";
			break;
			
			case 10:
				month = "Octubre";
			break;
			
			case 11:
				month = "Noviembre";
			break;
		
			case 12:
				month = "Diciembre";
			break;			
		}
		return month;
	}
}
