package Utility;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ProcessDate {

	public ProcessDate(){
		
	}
	
	public static String getCurrentLocalDateString(){
		Calendar c=  Calendar.getInstance(java.util.Locale.TAIWAN);
		return String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", c);
	} 
	public static Date getCurrentLocalDate(){
		Date d = new Date(System.currentTimeMillis());
		return d;
	}
	public static String DateParseToString(Date d){
		String tr = String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", d);
		return tr;
	}

	public static java.util.Date StringToUDate(String dateString){
		DateFormat dt = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			return (java.util.Date) dt.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String parseTime(String time) throws ParseException {
		String dateStr = time;			
		String pattern = "yyyyMMddhhmmss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);	
		java.util.Date date =  simpleDateFormat.parse(dateStr);
        DateFormat DateFormat = new SimpleDateFormat("YYYY年MM月dd日");
		String MsgDate = DateFormat.format(date);
		return MsgDate;
	}
	public static String extraYMD(String dateString){
		String extra = dateString.substring(0, 8);
		System.out.println("extraYMD = "+extra);
		return extra;
	}
}///-- class end
