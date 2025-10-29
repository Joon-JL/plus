package com.sec.clm.search.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtil {
	
	public static String getChangeDateFormat(String changeDate) {
		if("".equals(changeDate)) {
			return changeDate;
		} else {
			String[] tmpDate = changeDate.split("-");
//			String convertDate = tmpDate[2] + "/" + tmpDate[1] + "/" +tmpDate[0];
			return tmpDate[2] + "/" + tmpDate[1] + "/" +tmpDate[0];
		}		
	}
	
	//오늘 날짜
	public static String toDay(String format){
		java.util.Date date=new java.util.Date();
		java.text.SimpleDateFormat formatter=new java.text.SimpleDateFormat(format); 
		return formatter.format(date); 
	}

	//금주의 시작일과 종료일을 담은 배열 구하기
	public static String[] getMinMaxDaysOfWeek(String format) {
		GregorianCalendar gc = new GregorianCalendar();
//		SimpleDateFormat sdf = new SimpleDateFormat(format);
		SimpleDateFormat sdf = new SimpleDateFormat(format, java.util.Locale.KOREA);
		String days[] = new String	[2];
		gc.set(Calendar.DAY_OF_WEEK, gc.getMinimum(Calendar.DAY_OF_WEEK));//1
		days[0] = sdf.format(gc.getTime()).toString();
		gc.set(Calendar.DAY_OF_WEEK, gc.getMaximum(Calendar.DAY_OF_WEEK));//7
		days[1] = sdf.format(gc.getTime()).toString();
		return days;
	}
	//금월의 시작일과 종료일을 담은 배열 구하기
	public static String[] getMinMaxDaysOfMonth(String format) {
		GregorianCalendar gc = new GregorianCalendar();
//		SimpleDateFormat sdf = new SimpleDateFormat(format);
		SimpleDateFormat sdf = new SimpleDateFormat(format, java.util.Locale.KOREA);
		String days[] = new String	[2];
		gc.set(Calendar.DAY_OF_MONTH, gc.getMinimum(Calendar.DAY_OF_MONTH));//1
		days[0] = sdf.format(gc.getTime()).toString();
		gc.set(Calendar.DAY_OF_MONTH, gc.getMaximum(Calendar.DAY_OF_MONTH));//30 or 31
		days[1] = sdf.format(gc.getTime()).toString();
		return days;
	}

}
