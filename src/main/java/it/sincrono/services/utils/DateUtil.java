package it.sincrono.services.utils;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;

public class DateUtil {
    public static Boolean dateCompare(Date date,Date otherDate){
    	
    	
    	if(date==null && otherDate==null ) {
    		
    		return true;
    	}else if(date==null || otherDate==null) {
    		
    		return false;
    	}
    	

         Calendar calendarDate = Calendar.getInstance();
         calendarDate.setTime(date);
         

         Calendar calendarOtherDate = Calendar.getInstance();
         calendarOtherDate.setTime(otherDate);
         
         if(calendarDate.get(Calendar.DAY_OF_MONTH)==calendarOtherDate.get(Calendar.DAY_OF_MONTH)
        	&& calendarDate.get(Calendar.MONTH) + 1==calendarOtherDate.get(Calendar.MONTH) + 1
        	&& calendarDate.get(Calendar.YEAR)==calendarOtherDate.get(Calendar.YEAR)) {
        	 
        	 return true;
         }else {
        	 
        	 return false;
         }
        
       
           
       
    }
}
