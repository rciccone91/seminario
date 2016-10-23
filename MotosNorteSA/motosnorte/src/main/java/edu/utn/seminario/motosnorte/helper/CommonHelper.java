package edu.utn.seminario.motosnorte.helper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.Rol;

public class CommonHelper {
	
	public static String convertToOracleBoolean(Boolean val){
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
