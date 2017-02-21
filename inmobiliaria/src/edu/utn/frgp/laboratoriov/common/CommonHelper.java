package edu.utn.frgp.laboratoriov.common;

import java.util.Calendar;
import java.util.Date;

public class CommonHelper {
	
	public static String convertToMySqlBoolean(Boolean val){
		if(val){
			return "Y";
		}else{
			return "N";
		}
	}
	
	public static Boolean convertToJavaBoolean(String val){
		if("Y".equals(val)){
			return true;
		}else{
			return false;
		}
	}
	
	public static Date getYesterdayDate(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return cal.getTime();	
	}
}
