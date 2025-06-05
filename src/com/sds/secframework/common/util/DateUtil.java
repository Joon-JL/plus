package com.sds.secframework.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;

/**
* 날짜와 관련된 메쏘드를 정의한다
*/

public final class DateUtil
{
	public final static int SUNDAY = 1;
	public final static int MONDAY = 2;
	public final static int TUESDAY = 3;
	public final static int WEDNESDAY = 4;
	public final static int THURSDAY = 5;
	public final static int FRIDAY = 6;
	public final static int SATURDAY = 7;
	
	static String year		= "";
	static String month		= "";
	static String day		= "";
	static String today		= "";
	static String todayNnow	= "";
	static String subStr	= "/";
	static String subTimeStr= ":";


	/**
	 * 현재시간을 long형(milliseconds)으로 반환한다.
	 * 
	 * @return long
	 */
	public static long getNow()
	{
		return new Date().getTime();
	}

	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @return long
	 */
	public static long getInterval(long startTime, long endTime)
	{
		return endTime - startTime;
	}	

	/**
	* 주어진 날짜를 yyyyMMddHHmmss 형식으로 돌려줌 <BR>
	*
	* @param date Date 객체
	* @return yyyyMMddHHmmss 형식의 14자리 날짜
	*/

	public static String getDate(Date date)
	{
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		return simpledateformat.format(date);
	}

	/**
	* 주어진 날짜를 yyyyMMdd 형식으로 돌려줌 <BR>
	*
	* @param date Date 객체
	* @return yyyyMMdd 형식의 8자리 날짜
	*/

	public static String getShortDate(Date date)
	{
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		return simpledateformat.format(date);
	}

	/**
	* yyyyMMdd 형식의 날짜문자열을 원하는 캐릭터(ch)로 쪼개 돌려준다 <br>
	* <pre>
	*     ex) 20030405, ch(.) -> 2003.04.05
	*     ex) 200304, ch(.) -> 2003.04
	* </pre>
	*
	* @param date yyyyMMdd 형식의 날짜문자열
	* @param ch 구분자
	* @return 변환된 문자열
	*/

	public static String formatDate(String date, String ch)
	{
		if (date == null || date.trim().length() == 0)
			return "";

		String str = date.trim();
		String yyyy = "";
		String mm = "";
		String dd = "";

		if (str.length() == 8)
		{
			yyyy = str.substring(0, 4);
			if (yyyy.equals("0000"))
				return "";

			mm = str.substring(4, 6);
			if (mm.equals("00"))
				return yyyy;

			dd = str.substring(6, 8);
			if (dd.equals("00"))
				return yyyy + ch + mm;

			return yyyy + ch + mm + ch + dd;
		}
		else if (str.length() == 6)
		{
			yyyy = str.substring(0, 4);
			if (yyyy.equals("0000"))
				return "";

			mm = str.substring(4, 6);
			if (mm.equals("00"))
				return yyyy;

			return yyyy + ch + mm;
		}
		else if (str.length() == 4)
		{
			yyyy = str.substring(0, 4);
			if (yyyy.equals("0000"))
				return "";
			else
				return yyyy;
		}
		else
			return "";
	}

	/**
	* yyyyMMdd 형식의 날짜문자열을 KR Format으로 돌려준다 <br>
	* <pre>
	*     ex) 20030405 -> 2003년 4월 5일
	* </pre>
	*
	* @param date yyyyMMdd 형식의 날짜문자열
	* @return 변환된 문자열
	*/

	public static String formatDateKR(String date)
	{
		if (date == null || date.trim().length() == 0 || date.trim().length() != 8)
			return "";

		String str = date.trim();
		String yyyy = "";
		String mm = "";
		String dd = "";

		yyyy = str.substring(0, 4);
		mm = str.substring(4, 6);
		dd = str.substring(6);

		mm = mm.startsWith("0") ? (" " + mm.substring(1)) : mm;
		dd = dd.startsWith("0") ? (" " + dd.substring(1)) : dd;

		return yyyy + "년 " + mm + "월 " + dd + "일";
	}

	/**
	* HH24MISS 형식의 시간문자열을 원하는 캐릭터(ch)로 쪼개 돌려준다 <br>
	* <pre>
	*     ex) 151241, ch(/) -> 15/12/31
	* </pre>
	*
	* @param str HH24MISS 형식의 시간문자열
	* @param ch 구분자
	* @return 변환된 문자열
	*/

	public static String formatTime(String str, String ch)
	{
		if (str == null || str.length() == 0)
			return "";
		if (str.length() == 6)
		{
			return str.substring(0, 2) + ch + str.substring(2, 4) + ch + str.substring(4, 6);
		}
		else
			return "";
	}

	/**
	* 주어진 날짜가 속한 주의 월요일/일요일 날짜를 구해 스트링배열로 리턴한다 <br>
	*
	* @param str yyyymmdd 형식의 날짜문자열
	* @param week 주어진날짜가 속한 주에서의 차이, 1 : 다음주, -1 : 전주
	* @return 월/일요일
	* @exception ParseException 문자열파싱시 발생
	*/

	public static String[] getBothDayOfWeek(String str, int week) throws ParseException
	{
		return new String[] { getFirstDayOfWeek(str, week), getLastDayOfWeek(str, week)};
	}

	/**
	* 주어진 날짜가 속한 주의 월요일/일요일 날짜를 구해 스트링배열로 리턴한다 <br>
	*
	* @param date Date객체
	* @param week 주어진날짜가 속한 주에서의 차이, 1 : 다음주, -1 : 전주
	* @return 월/일요일
	* @exception ParseException 문자열파싱시 발생
	*/

	public static String[] getBothDayOfWeek(Date date, int week) throws ParseException
	{
		return new String[] { getFirstDayOfWeek(date, week), getLastDayOfWeek(date, week)};
	}

	/**
	* 주어진 날짜가 속한 주의 월요일 날짜를 구한다 <br>
	*
	* @param str yyyymmdd 형식의 날짜문자열
	* @param week 주어진날짜가 속한 주에서의 차이, 1 : 다음주, -1 : 전주
	* @return 월요일
	* @exception ParseException 문자열파싱시 발생
	*/

	public static String getFirstDayOfWeek(String str, int week) throws ParseException
	{
		String conStr = null;
		int dayOfWeek = 0;

		if (week == 0)
		{
			conStr = str;
			dayOfWeek = getCalendar(conStr).get(Calendar.DAY_OF_WEEK);
		}
		else
		{
			conStr = addDays(str, week * 7);
			dayOfWeek = getCalendar(conStr).get(Calendar.DAY_OF_WEEK);
		}

		int gap = 0;
		if (dayOfWeek != 1)
			gap = dayOfWeek - 2;
		else
			gap = 6;

		return addDays(conStr, -gap);
	}

	/**
	* 주어진 날짜가 속한 주의 월요일 날짜를 구한다 <br>
	*
	* @param date Date객체
	* @param week 주어진날짜가 속한 주에서의 차이, 1 : 다음주, -1 : 전주
	* @return 월요일
	* @exception ParseException 문자열파싱시 발생
	*/

	public static String getFirstDayOfWeek(Date date, int week) throws ParseException
	{
		Date conDate = null;
		int dayOfWeek = 0;

		if (week == 0)
		{
			conDate = date;
			dayOfWeek = getCalendar(conDate).get(Calendar.DAY_OF_WEEK);
		}
		else
		{
			conDate = addDays2Date(date, week * 7);
			dayOfWeek = getCalendar(conDate).get(Calendar.DAY_OF_WEEK);
		}

		int gap = 0;
		if (dayOfWeek != 1)
			gap = dayOfWeek - 2;
		else
			gap = 6;

		return addDays(conDate, -gap);
	}

	/**
	* 주어진 날짜가 속한 주의 일요일 날짜를 구한다 <br>
	*
	* @param str yyyymmdd 형식의 날짜문자열
	* @param week 주어진날짜가 속한 주에서의 차이, 1 : 다음주, -1 : 전주
	* @return 월요일
	* @exception ParseException 문자열파싱시 발생
	*/

	public static String getLastDayOfWeek(String str, int week) throws ParseException
	{
		String conStr = null;
		int dayOfWeek = 0;

		if (week == 0)
		{
			conStr = str;
			dayOfWeek = getCalendar(conStr).get(Calendar.DAY_OF_WEEK);
		}
		else
		{
			conStr = addDays(str, week * 7);
			dayOfWeek = getCalendar(conStr).get(Calendar.DAY_OF_WEEK);
		}

		int gap = 0;
		if (dayOfWeek != 1)
			gap = 8 - dayOfWeek;
		else
			gap = 0;

		return addDays(conStr, gap);
	}

	/**
	* 주어진 날짜가 속한 주의 일요일 날짜를 구한다 <br>
	*
	* @param date Date객체
	* @param week 주어진날짜가 속한 주에서의 차이, 1 : 다음주, -1 : 전주
	* @return 월요일
	* @exception ParseException 문자열파싱시 발생
	*/

	public static String getLastDayOfWeek(Date date, int week) throws ParseException
	{
		Date conDate = null;
		int dayOfWeek = 0;

		if (week == 0)
		{
			conDate = date;
			dayOfWeek = getCalendar(conDate).get(Calendar.DAY_OF_WEEK);
		}
		else
		{
			conDate = addDays2Date(date, week * 7);
			dayOfWeek = getCalendar(conDate).get(Calendar.DAY_OF_WEEK);
		}

		int gap = 0;
		if (dayOfWeek != 1)
			gap = 8 - dayOfWeek;
		else
			gap = 0;

		return addDays(conDate, gap);
	}
	/**
	 * 입년받은 년도 와 주을 입력 받아  해당 하는 주가 몇일 부터 몇일까지 (월~ 일) 구한다
	 * @param str yyyy
	 * @return yyyy-mm-dd ~ yyyy-mm-dd
	 * @throws ParseException 
	 */
	public static String getFirstDayOfWeek(String year,String week) throws ParseException{
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		Date date = fmt.parse(year+"0101");
		date.setTime(date.getTime() + (long) (Integer.parseInt(week)-1) * 7 * 1000L * 60L * 60L * 24L);
		return formatDate(getFirstDayOfWeek(date,0),"-");
		
	}
	public static String getLastDayOfWeek(String year,String week) throws ParseException{
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		Date date = fmt.parse(year+"0101");
		date.setTime(date.getTime() + (long) (Integer.parseInt(week)-1) * 7 * 1000L * 60L * 60L * 24L);
		return formatDate(getLastDayOfWeek(date,0),"-");
		
	}

	/**
	* 주어진 날짜가 속한 주가 월의 몇째주인지를 구한다 <br>
	*
	* @param str yyyymmdd 형식의 날짜문자열
	* @return 몇째주
	*/

	public static int getWeek(String str)
	{
		return getCalendar(str).get(Calendar.WEEK_OF_MONTH);
	}

	/**
	* 주어진 날짜가 속한 주가 월의 몇째주인지를 구한다 <br>
	*
	* @param date Date객체
	* @return 몇째주
	*/

	public static int getWeek(Date date)
	{
		return getCalendar(date).get(Calendar.WEEK_OF_MONTH);
	}
	
	/**
	 * 주어진 날짜가 속한 주가 년의 몇째주인지를 구한다
	 * @param str
	 * @return 몇째주
	 * @author Hyunjin.Cha
	 */
	public static String getWeekOfYear(String str){
		int returnVal = getCalendar(str).get(Calendar.WEEK_OF_YEAR);
		return Integer.toString(returnVal);
	}
	/**
	 * 주어진 날짜가 속한 주가 년의 몇째주인지를 구한다
	 * @param date
	 * @return 몇째주 
	 * @author Hyunjin.Cha
	 */
	public static String getWeekOfYear(Date date){
		int returnVal = getCalendar(date).get(Calendar.WEEK_OF_YEAR);
		return Integer.toString(returnVal);	
	}

	/**
	* 주어진 문자열로 날짜셋팅한 calendar class 구하기 <br>
	*
	* @param str yyyymmdd 형식의 날짜문자열
	* @return Calendar-인스턴스
	*/

	public static Calendar getCalendar(String str)
	{
		int yy = Integer.parseInt(str.substring(0, 4));
		int mm = Integer.parseInt(str.substring(4, 6)) - 1;
		int dd = Integer.parseInt(str.substring(6, 8));

		Calendar cal = Calendar.getInstance();
		cal.set(yy, mm, dd);
		return cal;
	}

	/**
	* 주어진 문자열로 날짜셋팅한 calendar class 구하기 <br>
	*
	* @param date Date객체
	* @return Calendar-인스턴스
	*/

	public static Calendar getCalendar(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
	* 날짜문자열(yyyymmdd)에 날짜를 더한(혹은 뺀) 일자를 구함 <br>
	*
	* @param str yyyyMMdd 형식의 날짜문자열
	* @param days 더할, 혹은 뺄 날수
	* @return yyyyyMMdd 형식의 8자리 날짜
	* @exception 날짜문자열 형식이 잘못되었을 경우 ParseException return
	*/

	public static String addDays(String str, int days) throws ParseException
	{
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		Date date = fmt.parse(str);
		date.setTime(date.getTime() + (long) days * 1000L * 60L * 60L * 24L);
		return fmt.format(date);
	}

	/**
	* date에 날짜를 더한(혹은 뺀) 일자를 구함 <br>
	*
	* @param date Date객체
	* @param days 더할, 혹은 뺄 날수
	* @return yyyyyMMdd 형식의 8자리 날짜
	* @exception 날짜문자열 형식이 잘못되었을 경우 ParseException return
	*/

	public static String addDays(Date date, int days) throws ParseException
	{
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		date.setTime(date.getTime() + (long) days * 1000L * 60L * 60L * 24L);
		return fmt.format(date);
	}

	/**
	* 날짜문자열(yyyymmdd)에 날짜를 더한(혹은 뺀) 일자를 구함 <br>
	*
	* @param str yyyyMMdd 형식의 날짜문자열
	* @param days 더할, 혹은 뺄 날수
	* @return yyyyyMMdd 형식의 8자리 날짜
	* @exception 날짜문자열 형식이 잘못되었을 경우 ParseException return
	*/

	public static Date addDays2Date(String str, int days) throws ParseException
	{
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		Date date = fmt.parse(str);
		date.setTime(date.getTime() + (long) days * 1000L * 60L * 60L * 24L);
		return date;
	}

	/**
	* 날짜문자열(yyyymmdd)에 날짜를 더한(혹은 뺀) 일자를 구함 <br>
	*
	* @param date Date객체
	* @param days 더할, 혹은 뺄 날수
	* @return date
	* @exception 날짜문자열 형식이 잘못되었을 경우 ParseException return
	*/

	public static Date addDays2Date(Date date, int days) throws ParseException
	{
		date.setTime(date.getTime() + (long) days * 1000L * 60L * 60L * 24L);
		return date;
	}

	/**
	* 주어진 문자열이 주어진 요일과 일치하는지 아닌지 여부리턴 <br>
	*
	* @param str yyyyMMdd 형식의 날짜문자열
	* @param dayOfWeek 요일 (SUNDAY~SATURDAY중 하나)
	* @return 날짜와 요일이 일치할시 true,아니면 false를 리턴한다
	*/

	public static boolean isDayOfWeek(String str, int dayOfWeek)
	{
		int day = getCalendar(str).get(Calendar.DAY_OF_WEEK);
		if (day == dayOfWeek)
			return true;
		else
			return false;
	}

	/**
	 * 주어진 문자열이 주어진 요일과 일치하는지 아닌지 여부리턴 <br>
	 *
	 * @param date Date객체
	 * @param dayOfWeek 요일 (SUNDAY~SATURDAY중 하나)
	 * @return 날짜와 요일이 일치할시 true,아니면 false를 리턴한다
	 */

	public static boolean isDayOfWeek(Date date, int dayOfWeek)
	{
		int day = getCalendar(date).get(Calendar.DAY_OF_WEEK);
		if (day == dayOfWeek)
			return true;
		else
			return false;
	}

	/**
	* 주어진 날짜가 속한 주의 요일을 구함 <br>
	*
	* @param str yyyymmdd 형식의 날짜
	* @param dayOfWeek 요일 (SUNDAY ~ SATURDAY 중 하나)
	* @return yyyyyMMdd 형식의 8자리 날짜
	*/

	public static String getSpecialDayOfWeek(String str, int dayOfWeek) throws ParseException
	{
		int gap = 0;
		int day = getCalendar(str).get(Calendar.DAY_OF_WEEK);

		if (day == dayOfWeek)
			return str;
		else if (dayOfWeek == SUNDAY)
			gap = 8 - day;
		else if (day < dayOfWeek)
			gap = dayOfWeek - day;
		else
			gap = - (day - dayOfWeek);

		if (day == SUNDAY)
			gap = gap - 7;
		return addDays(str, gap);
	}

	/**
	* 14자리 (yyyyMMddHH24MISS) 형식의 날짜를 04/05 (MM/dd) 로 바꿈
	*
	* @param date yyyyMMddHH24MISS 형식의 날짜
	* @param delim 구분자
	* return MMDD 월일문자열
	*/

	public static String convertDayOnly(String date, String delim)
	{
		return date.substring(4, 6) + delim + date.substring(6, 8);
	}
	
	  /**
	  * 전월 년/월/마지막날을 리턴한다.
	  *
	  * @return    전월 년월일시분초(yyyyMMdd)
	  */
		public static String beforeMonthEndSub()
		{
			dateLoad();
			int imonth = Integer.parseInt(month);
	        imonth = imonth-1;
			month = Integer.toString(imonth);
	        if (month.length() == 1) month = "0"+ month;

			String beforeMonthEndSub	= year + subStr+ month + subStr+ lastDay(Integer.parseInt(year), imonth);

			return beforeMonthEndSub;
		}


		    public static int lastDay(int year, int month){
	        int day = 0;
	        switch(month)
	        {
	            case 1:
	            case 3:
	            case 5:
	            case 7:
	            case 8:
	            case 10:
	            case 12: day = 31;
	                     break;
	            case 2: if ((year % 4) == 0) {
	                        if ((year % 100) == 0 && (year % 400) != 0) { day = 28; }
	                        else { day = 29; }
	                    } else { day = 28; }
	                    break;
	            default: day = 30;
	        }
	        return day;
	    }
		    
	    /**
	     * 전역변수를 현재시간으로 셋팅한다.
	     */
	   	public static void dateLoad()
	   	{
	   		//현재 날짜 출력
	   		SimpleDateFormat formatter	= new SimpleDateFormat("yyyyMMddHHmmss");
	   		Calendar calendar			= Calendar.getInstance(java.util.Locale.UK);  // 영국 지역으로 시간을 가져오죠.
	   		Date getTime				= calendar.getTime();
	   		String dateString			= formatter.format(getTime);

	   		year		= dateString.substring(0, 4);
	   		month		= dateString.substring(4, 6);
	   		day			= dateString.substring(6, 8);
	   		today		= dateString.substring(0, 8);
	   		todayNnow	= dateString.substring(0,14);
	   	}		
	   	
	    /**
	     * 현재년도를 리턴한다.
	     *
	     * @return    현재년도(yyyy)
	     */
	   	public static String year()
	   	{
	   		dateLoad();
	   		return year;
	   	}

	     /**
	     * 현재월을 리턴한다.
	     *
	     * @return    현재월(MM)
	     */
	   	public static String month()
	   	{
	   		dateLoad();
	   		return month;
	   	}

	     /**
	     * 현재일을 리턴한다.
	     *
	     * @return    현재일(dd)
	     */
	   	public static String day()
	   	{
	   		dateLoad();
	   		return day;
	   	}

	     /**
	     * 현재 년월일을 리턴한다.
	     *
	     * @return    현재 년월일(yyyyMMdd)
	     */
	   	public static String today()
	   	{
	   		dateLoad();
	   		return today;
	   	}

	     /**
	     * 현재 년월일시을 리턴한다.
	     *
	     * @return    현재 년월일시분초(yyyyMMddHHmmss)
	     */
	   	public static String todayNnow()
	   	{
	   		dateLoad();
	   		return todayNnow;
	   	}	   	
	   	
	    /**
	     * yyyyMMdd를 yyyy/MM/dd로 리턴한다.
	     *
	     * @param	   dateStr	바꿀년월일(yyyyMMdd)
	     * @return    년월일(yyyy/MM/dd)
	     */
	   	public static String dateIn(String dateStr)
	   	{
	   		dateLoad();
	   		String dateSub	= "";
	   		if( dateStr != null){
	   			if ( (! dateStr.equals("")) && (dateStr.length() >= 8) )
	   			{
	   				dateSub	= dateStr.substring(0, 4) + subStr
	   						+ dateStr.substring(4, 6) + subStr
	   						+ dateStr.substring(6, 8);
	   			}
	   		}else{
	   			dateSub = "";
	   		}

	   		return dateSub;
	   	}

	     /**
	     * yyyyMMdd를 yyyy.MM.dd로 리턴한다.
	     *
	     * @param	   dateStr	바꿀년월일(yyyyMMdd)
	     * @return    년.월.일(yyyy.MM.dd)
	     */
	   	public static String dateInMain(String dateStr)
	   	{
	   		dateLoad();
	   		String dateSub	= "";
	   		if ( (! dateStr.equals("")) && (dateStr.length() >= 8) )
	   		{
	   			dateSub	= dateStr.substring(0, 4) + "."
	   					+ dateStr.substring(4, 6) + "."
	   					+ dateStr.substring(6, 8);
	   		}

	   		return dateSub;
	   	}

	     /**
	     * yyyyMMdd를 yyyy/MM로 리턴한다.
	     *
	     * @param	   dateStr	바꿀년월일(yyyyMMdd)
	     * @return    년/월(yyyy/MM)
	     */
	   	public static String dateInMonth(String dateStr)
	   	{
	   		String dateSub	= "";
	   		if ( (! dateStr.equals("")) && (dateStr.length() >= 6) )
	   		{
	   			dateSub	= dateStr.substring(0, 4) + subStr
	   					+ dateStr.substring(4, 6);
	   		}

	   		return dateSub;
	   	}


	     /**
	     * yyyyMMddHHmmss를 yyyy/MM/dd HH:mm:ss로 리턴한다.
	     *
	     * @param	   dateStr	바꿀년월일시분초(yyyyMMddHHmmss)
	     * @return    년/월/일 시:분:초(yyyy/MM/dd HH:mm:ss)
	     */
	   	public static String dateNtimeIn(String dateStr)
	   	{
	   		dateLoad();
	   		String dateSub	= "";
	   		if ( (! dateStr.equals("")) && (dateStr.length() >= 14) )
	   		{
	   			dateSub	= dateStr.substring(0,  4) + subStr
	   					+ dateStr.substring(4,  6) + subStr
	   					+ dateStr.substring(6,  8) + " "
	   					+ dateStr.substring(8, 10) + ":"
	   					+ dateStr.substring(10,12) + ":"
	   					+ dateStr.substring(12,14);
	   		}

	   		return dateSub;
	   	}
	     /**
	     * yyyy/MM/dd HH:mm:ss를 yyyyMMddHHmmss로 리턴한다.
	     *
	     * @param	   dateStr	바꿀 yyyy/MM/dd HH:mm:ss
	     * @return    yyyyMMddHHmmss
	     */
	   	public static String dateNtimeOut(String dateStr)
	   	{
	   		dateLoad();
	   		String strDateOut	= "";

	   		if ( ! dateStr.equals("") )
	   		{
	   			StringTokenizer strTokenizer	= new StringTokenizer(dateStr, subStr);
	   			StringBuffer	strBuffer		= new StringBuffer();

	   			while(strTokenizer.hasMoreTokens())
	   			{
	   				strBuffer.append(strTokenizer.nextToken().trim());
	   			}
	   			strDateOut	= strBuffer.toString();

	   			strTokenizer	= new StringTokenizer(strDateOut, subTimeStr);
	   			strBuffer		= new StringBuffer();

	   			while(strTokenizer.hasMoreTokens())
	   			{
	   				strBuffer.append(strTokenizer.nextToken().trim());
	   			}
	   			strDateOut	= strBuffer.toString();

	   		}

	   		return strDateOut;
	   	}

	     /**
	     * yyyyMMddHHmm를 yyyy/MM/dd HH:mm로 리턴한다.
	     *
	     * @param	   dateStr	바꿀년월일시분초(yyyyMMddHHmm)
	     * @return    년/월/일 시:분:초(yyyy/MM/dd HH:mm)
	     */
	   	public static String dateNtimeMinIn(String dateStr)
	   	{
	   		dateLoad();
	   		String dateSub	= "";
	   		if ( (! dateStr.equals("")) && (dateStr.length() >= 12) )
	   		{
	   			dateSub	= dateStr.substring(0,  4) + subStr
	   					+ dateStr.substring(4,  6) + subStr
	   					+ dateStr.substring(6,  8) + " "
	   					+ dateStr.substring(8, 10) + ":"
	   					+ dateStr.substring(10,12);
	   		}

	   		return dateSub;
	   	}
	     /**
	     * yyyy/MM/dd HH:mm를 yyyyMMddHHmm로 리턴한다.
	     *
	     * @param	   dateStr	바꿀 yyyy/MM/dd HH:mm
	     * @return    yyyyMMddHHmmss
	     */
	   	public static String dateNtimeMinOut(String dateStr)
	   	{
	   		dateLoad();
	   		String strDateOut	= "";

	   		if ( ! dateStr.equals("") )
	   		{
	   			StringTokenizer strTokenizer	= new StringTokenizer(dateStr, subStr);
	   			StringBuffer	strBuffer		= new StringBuffer();

	   			while(strTokenizer.hasMoreTokens())
	   			{
	   				strBuffer.append(strTokenizer.nextToken().trim());
	   			}
	   			strDateOut	= strBuffer.toString();

	   			strTokenizer	= new StringTokenizer(strDateOut, subTimeStr);
	   			strBuffer		= new StringBuffer();

	   			while(strTokenizer.hasMoreTokens())
	   			{
	   				strBuffer.append(strTokenizer.nextToken().trim());
	   			}
	   			strDateOut	= strBuffer.toString();

	   		}

	   		return strDateOut;
	   	}

	     /**
	     * HHmmss를 HH:mm:ss로 리턴한다.
	     *
	     * @param	   dateStr	바꿀시분초(HHmmss)
	     * @return    시:분:초(HH:mm:ss)
	     */
	   	public static String timeIn(String dateStr)
	   	{
	   		dateLoad();
	   		String dateSub	= "";
	   		if ( (! dateStr.equals("")) && (dateStr.length() >= 6) )
	   		{
	   			dateSub	= dateStr.substring(0,  2) + subTimeStr
	   					+ dateStr.substring(2,  4) + subTimeStr
	   					+ dateStr.substring(4,  6) ;
	   		}

	   		return dateSub;
	   	}

	     /**
	     * HH:mm:ss를 HHmmss로 리턴한다.
	     *
	     * @param	   dateStr	바꿀시분초(HH:mm:ss)
	     * @return    (HHmmss)
	     */
	   	public static String timeOut(String dateStr)
	   	{
	   		dateLoad();
	   		String strDateOut	= "";

	   		if ( ! dateStr.equals("") )
	   		{
	   			StringTokenizer strTokenizer	= new StringTokenizer(dateStr, subTimeStr);
	   			StringBuffer	strBuffer		= new StringBuffer();

	   			while(strTokenizer.hasMoreTokens())
	   			{
	   				strBuffer.append(strTokenizer.nextToken().trim());
	   			}
	   			strDateOut	= strBuffer.toString();

	   		}

	   		return strDateOut;
	   	}


	     /**
	     * yyyy/MM/dd를 yyyyMMdd로 리턴한다.
	     *
	     * @param	   dateStr	바꿀 년/월/일(yyyy/MM/dd)
	     * @return    년월일(yyyyMMdd)
	     */
	   	public static String dateOut(String dateStr)
	   	{
	   		dateLoad();
	   		String strDateOut	= "";

	   		if ( ! dateStr.equals("") )
	   		{
	   			StringTokenizer strTokenizer	= new StringTokenizer(dateStr, subStr);
	   			StringBuffer	strBuffer		= new StringBuffer();

	   			while(strTokenizer.hasMoreTokens())
	   			{
	   				strBuffer.append(strTokenizer.nextToken());
	   			}
	   			strDateOut	= strBuffer.toString();
	   		}

	   		return strDateOut;
	   	}


	     /**
	     * yyyy-MM-dd를 yyyyMMdd로 리턴한다.(SAP Data)
	     *
	     * @param	   dateStr	바꿀 년-월-일(yyyy-MM-dd)
	     * @return    년월일(yyyyMMdd)
	     */
	   	public static String dateOutSap(String dateStr)
	   	{
	   		dateLoad();
	   		String strDateOut	= "";

	   		if ( ! dateStr.equals("") )
	   		{
	   			StringTokenizer strTokenizer	= new StringTokenizer(dateStr, "-");
	   			StringBuffer	strBuffer		= new StringBuffer();

	   			while(strTokenizer.hasMoreTokens())
	   			{
	   				strBuffer.append(strTokenizer.nextToken());
	   			}
	   			strDateOut	= strBuffer.toString();
	   		}

	   		return strDateOut;
	   	}

	     /**
	     * 받은 년월(yyyyMMdd)의 X개월 전월(yyyyMM)을 리턴한다.
	     *
	     * @param	   monthStr	바꿀 년월(yyyyMMdd or yyyy/MM/dd)
	     * @param	   iNo		몇개월전
	     * @return    년월일(yyyyMMdd)
	     */
	   	public static String minusMonth(String monthStr, int iNo)
	   	{
	   		dateLoad();
	   		String strLastMonth	= "";
	   		monthStr = dateOut(monthStr);

	   		String[] month	= {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
	   		int iYear		= Integer.parseInt(monthStr.substring(0,4));
	   		int iMonth		= Integer.parseInt(monthStr.substring(4,6)) - 1;
	   		String strDay	= monthStr.substring(6,8);
	   		int minusMonth	= iMonth-iNo;

	   		if ( (minusMonth) < 0 )
	   		{
	   			// 전년도로 바뀜
	   			strLastMonth	= Integer.toString(iYear-1) + month[12+minusMonth] + strDay;
	   		}
	   		else
	   		{
	   			strLastMonth	= Integer.toString(iYear) + month[minusMonth] + strDay;
	   		}

	   		return strLastMonth;
	   	}

	    /**
	     * 현재일 x 일 이후 또는 이전의 일자를 리턴한다.
	     * 15일 이전(-15), 20일 이후(20)
	     *
	     * @param	   iDay		몇일전
	     * @return    년월일(yyyyMMdd)
	     */
	   	public static String prevNnext(int iDay)
	   	{
	   		SimpleDateFormat formatter	= new SimpleDateFormat("yyyyMMddHHmmss");
	   		Calendar calendar			= Calendar.getInstance(Locale.getDefault());  // 한국 지역으로 시간을 가져오죠.
	   		calendar.add(Calendar.DATE,iDay);
	   		Date getTime				= calendar.getTime();
	   		String dateString			= formatter.format(getTime);

	   		return dateString;
	   	}
	     /**
	     * 현재일 x 일 이후 또는 이전의 일자를 리턴한다.
	     * 15일 이전(-15), 20일 이후(20)
	     *
	     * @param	   i		몇일후
	     * @return    년월일(yyyyMMdd)
	     */
	       public static String prevNnextSub(int i)
	       {
	           String s = "";
	           SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMddHHmmss");
	           Calendar calendar1 = Calendar.getInstance(Locale.getDefault());
	           calendar1.add(5, i);
	           Date date = calendar1.getTime();
	           s = dateIn(simpledateformat.format(date));
	           return s;
	       }

	     /**
	     * 현재 년월일시분초를 yyyy년MM월dd일HH시mm분ss초로 리턴한다.
	     *
	     * @param	   dateStr	바꿀 년월일시분초(yyyyMMddHHmmss)
	     * @return    yyyy년MM월dd일HH시mm분ss초
	     */
	   	public static String dateInFinish(String dateStr)
	   	{
	   		dateLoad();
	   		String dateSub	= "";
	   		if ( (! dateStr.equals("")) && (dateStr.length() >= 14) )
	   		{
	   			dateSub	= dateStr.substring(0,  4) + "년 "
	   					+ dateStr.substring(4,  6) + "월 "
	   					+ dateStr.substring(6,  8) + "일 "
	   					+ dateStr.substring(8, 10) + "시 "
	   					+ dateStr.substring(10,12) + "분 "
	   					+ dateStr.substring(12,14) + "초";
	   		}

	   		return dateSub;
	   	}	   	
	   	
		/**
		* 정해진 포맷으로 년월일시분초 받아오기
		*
		*
		* @param     format         날짜포맷
		* @return    String         
		*
		*/
		public static String getTime(String format){
			if ( format == null || format.equals("") == true )
				format = "yyyyMMddHHmmss";
		
			TimeZone tz = TimeZone.getDefault();
			tz.setRawOffset((60*60*1000) * 9);
			TimeZone.setDefault(tz);
			Calendar cal = Calendar.getInstance(tz);
			Date date = cal.getTime();
			SimpleDateFormat formater = new SimpleDateFormat(format, Locale.getDefault());
			String timestamp = formater.format(date);
		
			return timestamp;
		}
		
		/**
		 * COMBO BOX에 현재년도 +- 5년 반환<P>
		 *
		 * @param 
		 * @return YYYY 형식의 4자리
		 * @throws Exception
		*/
		public static List list5Year() throws Exception {
			ArrayList rtnValue = new ArrayList();
			
			Date toYear = new Date();
			SimpleDateFormat date = new SimpleDateFormat("yyyy",Locale.KOREA); 
			        	
			int nowYear = Integer.parseInt(date.format(toYear));
			
			for(int i=0; i <= 10; i++) {
				rtnValue.add(i, String.valueOf(nowYear + i - 5));
			}
			
			return rtnValue;
		}
		
		/**
		 * GMT시간변경<P>
		 *
		 * @param 
		 * @return YYYY 형식의 4자리
		 * @throws Exception
		*/
		public static String convertTimeZone(String time, String sourceTZ, String destTZ, String format) throws Exception  {
			//final String DATE_TIME_FORMAT = "yyyyMMdd-HH:mm:ss";
			final String DATE_TIME_FORMAT = format;
		    SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
		    Date specifiedTime;
		    
		    if (sourceTZ != null) {        
		    	sdf.setTimeZone(TimeZone.getTimeZone(sourceTZ));
		    } else {        
		    	sdf.setTimeZone(TimeZone.getDefault()); // default to server's timezone
		    }
		      
		    specifiedTime = sdf.parse(time);
		      
		   if (destTZ != null) {      
			   sdf.setTimeZone(TimeZone.getTimeZone(destTZ));
		   } else {      
			   sdf.setTimeZone(TimeZone.getDefault()); // default to server's timezone
		   }
		   return sdf.format(specifiedTime);
		}

		/**
		 * GMT시간변경<P>
		 * 한국시각 GMT+9을 각 GMT시각으로 변경
		 * @param 
		 * @return YYYY 형식의 4자리
		 * @throws Exception
		*/
		public static String convertTimeZone(String time, String destTZ, String format) throws Exception  {
			//final String DATE_TIME_FORMAT = "yyyyMMdd-HH:mm:ss";
			final String DATE_TIME_FORMAT = format;
		    SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
		    Date specifiedTime;
		    
	    	sdf.setTimeZone(TimeZone.getTimeZone("GMT+0"));
		      
		    specifiedTime = sdf.parse(time);
		      
		   if (destTZ != null) {      
			   sdf.setTimeZone(TimeZone.getTimeZone(destTZ));
		   } else {      
			   sdf.setTimeZone(TimeZone.getDefault()); // default to server's timezone
		   }
		   return sdf.format(specifiedTime);
		}
		
		/**
		 * 해당 분기의 첫날짜와 마지막 날짜를 구한다.
		 * 
		 * @param yyyy 연도
		 * @param quarter 분기
		 * @return result[0] : 분기의 시작일자, result[1] : 분기의 마지막 일자
		 *          yyyyMMdd 형태의 날짜이다.
		 * @throws Exception
		 */
		public static String[] getQuarterFromToDay(String yyyy, String quarter) throws Exception {
			return getQuarterFromToDay(yyyy, quarter, "") ;
		}
		
		/**
		 * 해당 분기의 첫날짜와 마지막 날짜를 구한다.
		 * 
		 * @param yyyy 연도
		 * @param quarter 분기
		 * @param dem 날짜포멧( ex) - : yyyy-MM-dd 형태)
		 * @return result[0] : 분기의 시작일자, result[1] : 분기의 마지막 일자
		 */
		public static String[] getQuarterFromToDay(String yyyy, String quarter, String dem) throws Exception {
			String[] result = new String[2] ;
			
			int startMonth = (Integer.parseInt(quarter)-1) * 3 + 1 ;
			int endMonth = startMonth + 2 ;
			
			
			result[0] = yyyy + dem + (startMonth<10 ? "0"+startMonth : startMonth) + dem + "01" ;
			result[1] = yyyy + dem + (endMonth<10 ? "0"+endMonth : endMonth) + dem + (lastDay(Integer.parseInt(yyyy), endMonth)) ;
			
			return result ;
		}
		
		/**
		 * 해당 달의 마지막날짜를 구해서 보낸다.
		 * 
		 * @param yyyyMM 분기를 구하고자 하는 달(yyyyMM 형태)
		 * @return yyyyMMdd 형태로 리턴한다.
		 */
		public static String lastDay(String yyyyMM) throws Exception {
			return lastDay(yyyyMM, "") ;
		}
		
		/**
		 * 해당 달의 마지막날짜를 구해서 보낸다.
		 * 
		 * @param yyyyMM 분기를 구하고자 하는 달(yyyyMM 형태)
		 * @param dem 날짜포멧( ex) - : yyyy-MM-dd 형태)
		 * @return 
		 */
		public static String lastDay(String yyyyMM, String dem) throws Exception {
			String result = null ;
			
			int year = Integer.parseInt(yyyyMM.substring(0,4)) ;
			int month = Integer.parseInt(yyyyMM.substring(4,6)) ;
			int day = lastDay(year, month);
			
			result = year + dem + (month<10 ? "0"+month : month) + dem + (day<10 ? "0"+day : day)  ; 
			
			return result ;
		}
}