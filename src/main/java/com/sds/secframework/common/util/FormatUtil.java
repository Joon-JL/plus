package com.sds.secframework.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
* FORMAT 변경에 관한 메쏘드를 정의한다
* 
*/

public final class FormatUtil
{
	/** 
	* int 을 원하는 포맷형태로 변환한다 <br>
	* 기본 포맷형태는 '###,###,##0.00'을 적용한다 <br>
	*
	* @param cnt 변환할 int
	* @param format 포맷형태
	* @return 변환된 문자열을 리턴한다
	*/
	
	public static String format( int cnt, String format )
	{
		DecimalFormat fmt = new DecimalFormat( format );
		return fmt.format( cnt );
	}
	
	/** 
	* long 을 원하는 포맷형태로 변환한다 <br>
	* 기본 포맷형태는 '###,###,##0.00'을 적용한다 <br>
	*
	* @param cnt 변환할 long
	* @param format 포맷형태
	* @return 변환된 문자열을 리턴한다
	*/
	
	public static String format( long cnt, String format )
	{
		DecimalFormat fmt = new DecimalFormat( format );
		return fmt.format( cnt );
	}
	
	/** 
	* double 을 원하는 포맷형태로 변환한다 <br>
	* 기본 포맷형태는 '###,###,##0.00'을 적용한다 <br>
	*
	* @param cnt 변환할 double
	* @param format 포맷형태
	* @return 변환된 문자열을 리턴한다
	*/
	
	public static String format( double cnt, String format )
	{
		DecimalFormat fmt = new DecimalFormat( format );
		return fmt.format( cnt );
	}
	
	/** 
	* double 을 원하는 포맷형태로 변환한다 <br>
	* 기본 포맷형태는 '###,###,##0.00'을 적용한다 <br>
	*
	* @param cnt 변환할 double
	* @return 변환된 문자열을 리턴한다
	*/
	
	public static String format( double cnt )
	{
		DecimalFormat fmt = new DecimalFormat( "###,###,##0.00" );
		return fmt.format( cnt ) ;
	}
	
	/** 
	* long 을 원하는 포맷형태로 변환한다 <br>
	* 기본 포맷형태는 '###,###,###'을 적용한다 <br>
	*
	* @param cnt 변환할 long
	* @return 변환된 문자열을 리턴한다
	*/
	
	public static String format( long cnt )
	{
		DecimalFormat fmt = new DecimalFormat( "###,###,###" );
		return fmt.format( cnt ) ;
	}
	
	/** 
	* int 를 원하는 포맷형태로 변환한다 <br>
	* 기본 포맷형태는 '###,###,###'을 적용한다 <br>
	*
	* @param cnt 변환할 int
	* @return 변환된 문자열을 리턴한다
	*/
	
	public static String format( int cnt )
	{
		DecimalFormat fmt = new DecimalFormat( "###,###,###" );
		return fmt.format( cnt ) ;
	}
	
	/** 
	* 숫자형 객체를 int로 변환한다.<br>
	*
	* @param cnt 변환할 숫자형 객체
	* @return 변환된 문자열을 리턴한다
	*/
	
	public static int formatInt(Object o)
	{
		int result = 0;
		
		if(o instanceof BigDecimal) {
			result = ((BigDecimal)o).intValue();
		} else if (o instanceof Integer) {
			result = ((Integer)o).intValue();
		}
		
		return result;
	}

	/**
	* 숫자형 객체를 String로 변환한다.<br>
	*
	* @param cnt 변환할 숫자형 객체
	* @return 변환된 문자열을 리턴한다
	*/
	
	public static String formatNumToString(Object o)
	{
		String result = "";
		
		if(o instanceof BigDecimal) {
			result = ((BigDecimal)o).toString();
		} else if (o instanceof Integer) {
			result = ((Integer)o).toString();
		}
		
		return result;
	}

}
