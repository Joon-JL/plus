package com.sds.secframework.common.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
* 문자열과 관련된 일반적인 메쏘드를 정의한다
*/

public final class StringUtil
{
	/**
	* 주어진 길이보다 길이가 작은 문자열을 앞에 0을 붙여 패딩한다 <BR>
	*
	* @param str 문자열
	* @param len 길이
	* @return 앞에 '0'으로 패딩된 문자열을 리턴한다. 단, 주어진 길이보다 크거나 같으면 원본문자열을 그대로 리턴한다
	*/

	public static String paddingZero(String str, int len)
	{
		int strLen = str.length();
		int cab = 0;
		String tmp = "";
		
		
		if (strLen >= len)
			return str;
		else
			cab = len - strLen;

		for (int ii = 0; ii < cab; ii++)
		{
			tmp = "0" + tmp;
		}

		return tmp + str;
	}

	/**
	* 첫문자를 대문자로 바꿈 <BR>
	*
	* @param str 입력문자열
	* @return 변환된 문자열을 리턴한다
	*/

	public static String initCap(String str)
	{
		// A: 65, a: 97
		// 소문자일 경우 대문자로 변경
		String second = str.substring(1);
		char h = str.charAt(0);
		if (h >= 'a' && h <= 'z')
		{
			String first = String.valueOf((char) (h - 32));
			return first + second;
		}
		else
			return str;
	}

	/**
	* 원본문자열(str)에서 캐릭터(ch)를 찾아 제거한다 <BR>
	*
	* @param str 입력문자열
	* @param ch 제거할 문자
	* @return 변환된 문자열을 리턴한다
	*/

	public static String removeChar(String str, char ch)
	{
		return removeChar(str, String.valueOf(ch));
	}

	/**
	* 원본문자열(str)에서 문자열(ch)을 찾아 제거한다 <BR>
	*
	* @param str 입력문자열
	* @param ch 제거할 문자열
	* @return 변환된 문자열을 리턴한다
	*/

	public static String removeChar(String str, String ch)
	{
		StringBuffer buff = new StringBuffer();
		StringTokenizer token = new StringTokenizer(str, ch);
		while (token.hasMoreTokens())
		{
			buff.append(token.nextToken());
		}

		return buff.toString();
	}

	/**
	* 문자열을 정수로 변환한다 <br>
	*
	* @param str 입력문자열
	* @return 입력문자열이 NULL 일 경우에는 0, 그외는 변환된 정수를 리턴한다. 
	*/

	public static int str2int(String str)
	{
		if (str == null)
			return 0;
		else
			return Integer.valueOf(str).intValue();
	}

	/**
	* 정수를 문자열로 변환한다 <br>
	*
	* @param i 입력정수
	* @return 변환된 문자열을 리턴한다. 
	*/

	public static String int2Str(int i)
	{
		return (new Integer(i)).toString();
	}

	/**
	* 널인 문자열을 스페이스("")로 치환한다 <BR>
	* 단, 좌우 공백이 있는 문자열은 trim 한다 <br>
	*
	* @param s 입력문자열
	* @return 치환된 문자열
	*/

	public static String null2space(String s)
	{
		if (s == null || s.length() == 0)
			return "";
		else
			return s.trim();
	}

	/**
	* 널이거나 빈 문자열을 원하는 스트링으로 변환한다 <BR>
	* 단, 좌우 공백이 있는 문자열은 trim 한다 <br>
	*
	* @param org 원본문자열
    * @param converted 널이나 빈 문자열인 경우 치환혈 문자열
	* @return null 이나 빈 문자열인 경우에는 converted, 그 이외에는 trim된 원본문자열
	*/

	public static String null2str(String org, String converted)
	{
		if (org == null || org.trim().length() == 0)
			return converted;
		else
			return org.trim();
	}

	/**
	* 문자열이 널이거나 빈 공백문자열인지 CHECK 한다 <br>
	*
	* @param str 원본문자열
	* @return 널이거나 공백일 경우 true, 아닐경우 false 를 리턴한다
	*/

	public static boolean isNull(String str)
	{
		if (str == null || str.trim().length() == 0)
			return true;
		else
			return false;
	}

	/**
     * <pre>
	 * 문자열에서 주어진 separator 로 쪼개어 문자배열을 생성한다.
     * 내부적으로 StringTokenizer를 사용하므로 separator는 delimiter들로 동작하며
     * seperator가 중복되는 경우 빈문자열은 return 배열에 포함되지 않는다.
     * 예) str ==> foo|+:bo++o
     *     separator ==> |+:
     *     return ==> {"foo", "bo", "o")
	 *
     *</pre>
     *
	 * @param str 원본문자열
	 * @param separator 원하는 token 문자열
	 * @return 스트링배열
	 */

	public static String[] split(String str, String separator)
	{
		StringTokenizer st = new StringTokenizer(str, separator);
		String[] values = new String[st.countTokens()];
		int pos = 0;
		while (st.hasMoreTokens())
		{
			values[pos++] = st.nextToken();
		}

		return values;
	}

    
    /**
     * <pre>
     * 문자열에서 주어진 separator 로 쪼개어 문자배열을 생성한다.
     * useToken이 true 인 경우 내부적으로 StringTokenizer를 사용하므로 separator는 delimiter들로 동작하며
     * seperator가 중복되는 경우 빈문자열은 return 배열에 포함되지 않는다.
     * 예) str ==> foo|+:|+:bo++o
     *     separator ==> |+:
     *     return ==> {"foo", "bo", "o")
     *
     * useToken이 false 인 경우는 separator 문자열 자체가 하나의 delimiter로 동작한다.
     * 예) str ==> foo|+:|+:bo++o
     *     separator ==> |+:
     *     return ==> {"foo", "", "bo++o")
     * 
     * </pre>
     * 
     * @param str 원본문자열
     * @param separator 원하는 token 문자열
     * @param useToken Token을 사용할 것인지에 대한 여부 
     * @return 스트링배열
     */
    public static String[] split(String str, String separator, boolean useToken) {
        if ( useToken == true ) {
            return split(str, separator);
        }
        else {
            int index = 0;
            String[] resultStrArray = new String[search(str,separator)+1];
            String strCheck = new String(str);
            while(strCheck.length() != 0) {
                int begin = strCheck.indexOf(separator);
                if(begin == -1) {
                    resultStrArray[index] = strCheck;
                    break;
                } else {
                    int end = begin + separator.length();
                    resultStrArray[index++] = strCheck.substring(0, begin);

                    strCheck = strCheck.substring(end);
                    if(strCheck.length()==0 ){
                        resultStrArray[index] = strCheck;
                        break;
                    }
                }
            }
            return resultStrArray;
           
        }
    }
    
	/**
	* 문자열 배열을 주어진 separator 로 연결하여 문자열을 생성한다 <br>
	*
	* @param list 스트링배열
	* @param separator 원하는 token 문자열
	* @return 합쳐진 문자열을 리턴한다
	*/

	public static String join(String list[], String separator)
	{
		StringBuffer csv = new StringBuffer();
		for (int i = 0; i < list.length; i++)
		{
			if (i > 0)
				csv.append(separator);
			csv.append(list[i]);
		}
		return csv.toString();
	}

	/** 
	* Exception 객체로 에러메시지 문자열을 생성한다 <br>
	*
	* @param e Throwable 객체
	* @return 에러문자열
	*/

	public static String stackTrace(Throwable e)
	{
		String str = "";
		try
		{
			ByteArrayOutputStream buff = new ByteArrayOutputStream();
			e.printStackTrace(new PrintWriter(buff, true));
			str = buff.toString();
		}
		catch (Exception f)
		{
		}

		return str;
	}

	/**
	* 원본문자열에서 뒤로부터 주어진 문자열(ch)을 찾아 제거한다 <br>
	*
	* @param str 원본문자열
	* @param ch 제거할 문자열
	* @return 제거된문자열을 리턴한다
	* @exception 파싱에러시 발생
	*/

	public static String removeLastChar(String str, String ch) throws Exception
	{
		int pos = str.lastIndexOf(ch);
		if (pos == -1)
			return str;
		else
			return str.substring(0, pos) + str.substring(pos + 1);
	}
    
    /**
     * 문자열대 문자열로 바꿔준다.
     * @param line 원본 문자열
     * @param oldString 변경전 String 
     * @param newString 변경후 String
     * @return oldString이 newString으로 변경된 문자열
     */
    public static String replace(String line, String oldString, String newString)
    {   
        line = null2space(line);
        for(int index = 0; (index = line.indexOf(oldString, index)) >= 0; index += newString.length())
            line = line.substring(0, index) + newString + line.substring(index + oldString.length());

        return line;
    }

    /**
     * 문자열의 왼쪽에 지정한 문자를 지정된 길이에 모자란 만큼 Add하는 메소드
     * 
     * @author handbell
     * @param str 원본 문자열
     * @param add 원본 문자열이 지정된 length 보다 적은 경우 length 만큼 채워질 문자
     * @param len 전체 길이
     * @return 전체 길이만큼 지정문자가 채워진 문자열. 단 원본 문자열의 길이가 전체 지정한 길이보다 긴 경우에는 원본 문자열이 return 됨
     */
    public static String leftPad(String str, char add, int len)
    {
        if (str == null)
            return "";

        for (int i = str.length(); i < len; i++)
        {
            str = add + str;
        }

        return str;
    }

    /**
     * 문자열의 오른쪽에 지정한 문자를 지정된 길이에 모자란 만큼 Add하는 메소드
     * 
     * @author handbell
     * @param str 원본 문자열
     * @param add 원본 문자열이 지정된 length 보다 적은 경우 length 만큼 채워질 문자
     * @param len 전체 길이
     * @return 전체 길이만큼 지정문자가 채워진 문자열. 단 원본 문자열의 길이가 전체 지정한 길이보다 긴 경우에는 원본 문자열이 return 됨
     */
    public static String rightPad(String str, char add, int len)
    {
        if (str == null)
            return "";

        for (int i = str.length(); i < len; i++)
        {
            str = str + add;
        }

        return str;
    }

    /**
     * 문자열의 왼쪽에 지정한 문자를 지정된 byte에 모자란 만큼 Add하는 메소드
     * 
     * @author handbell
     * @param str 원본 문자열
     * @param add 원본 문자열이 지정된 byte보다 적은 경우 byte 만큼 채워질 문자
     * @param blen 전체 byte
     * @return 전체 byte만큼 지정문자가 채워진 문자열. 단 원본 문자열의 byte가 전체 지정한 byte보다 긴 경우에는 원본 문자열이 return 됨
     */
    public static String leftPadB(String str, char add, int blen)
    {
        if (str == null)
            return "";

        for (int i = str.getBytes().length; i < blen; i++)
        {
            str = add + str;
        }

        return str;
    }

    /**
     * 문자열의 오른쪽에 지정한 문자를 지정된 byte에 모자란 만큼 Add하는 메소드
     * 
     * @author handbell
     * @param str 원본 문자열
     * @param add 원본 문자열이 지정된 byte보다 적은 경우 byte 만큼 채워질 문자
     * @param blen 전체 byte
     * @return  전체 byte만큼 지정문자가 채워진 문자열. 단 원본 문자열의 byte가 전체 지정한 byte보다 긴 경우에는 원본 문자열이 return 됨
     */
    public static String rightPadB(String str, char add, int blen)
    {
        if (str == null)
            return "";

        for (int i = str.getBytes().length; i < blen; i++)
        {
            str = str + add;
        }

        return str;
    }

    /**
     * 대상문자열(strTarget)에서 지정문자열(strSearch)이 검색된 횟수를,
     * 지정문자열이 없으면 0 을 반환한다.
     *
     * @param strTarget 대상문자열
     * @param strSearch 검색할 문자열
     * @return 지정문자열이 검색되었으면 검색된 횟수를, 검색되지 않았으면 0 을 반환한다.
     */
    private static int search(String strTarget, String strSearch){
        int result=0;
        String strCheck = new String(strTarget);
        for(int i = 0; i < strTarget.length(); ){
            int loc = strCheck.indexOf(strSearch);
            if(loc == -1) {
                break;
            } else {
                result++;
                i = loc + strSearch.length();
                strCheck = strCheck.substring(i);
            }
        }
        return result;
    }

    /**
     * 첫번째 변수를 체크하여 Null값이면 두번째 변수를 리턴하고, 아니면 그대로 리턴한다.
     *
     * @param     obj 체크할 값
     * @param     str Null일 경우 리턴할 값
     * @return    obj :Null일 경우 str, 아닐경우 obj
     */
       public static String nvl(Object obj, String str)
       {
           return obj != null ? obj.toString() : str;
       }

     /**
     * 첫번째 변수를 체크하여 Null값이면 두번째 변수를 리턴하고, 아니면 그대로 리턴한다.
     *
     * @param     str 체크할 값
     * @param     str1 Null일 경우 리턴할 값
     * @return    str :Null일 경우 str1, 아닐경우 str
     */
       public static String nvl(String str, String str1)
       {
           return str != null ? str : str1;
       }

     /**
     * 첫번째 변수를 체크하여 NULL 또는 ""값이면 두번째 변수를 리턴하고, 아니면 그대로 리턴한다.
     *
     * @param     obj 체크할 값
     * @param     str ""일 경우 리턴할 값
     * @return    obj : ""일 경우 str1, 아닐경우 obj
     */
       public static String bvl(Object obj, String str)
       {
           return obj != null && !obj.toString().equals("") ? obj.toString() : str;
       }

     /**
     * 첫번째 변수를 체크하여 NULL 또는 ""값이면 두번째 변수를 리턴하고, 아니면 그대로 리턴한다.
     *
     * @param     str 체크할 값
     * @param     str1 ""일 경우 리턴할 값
     * @return    str : ""일 경우 str1, 아닐경우 str
     */
       public static String bvl(String str, String str1)
       { 	   
           return str != null && !str.equals("") ? str : str1;
       }

       /**
        * 첫번째 변수를 체크하여 NULL 또는 ""값이면 두번째 변수를 리턴하고, 아니면 그대로 리턴한다.
        * mainControl 에서 사용
        * @param     str 체크할 값
        * @param     str1 ""일 경우 리턴할 값
        * @return    str : ""일 경우 str1, 아닐경우 str
        */
          public static String mainBvl(String str, String str1)
          {
       	   if(str != null) {
       		   str = str.replaceAll("<", "&lt;");
           	   str = str.replaceAll(">", "&gt;");
           	   str = str.replaceAll("'", "''");
           	   str = str.replaceAll("--", "__");
           	   str = str.replaceAll(";", "|	"); 
           	   str = str.replaceAll("%0a", " ");
       	   } 
       	   
              return str != null && !str.equals("") ? str : str1;
          }       
       
     /**
     * tokenStr을 구분자로 str값을 나눈다.
     *
     * @param     str 체크할 값
     * @param     tokenStr 구분자
     * @return    구분한 값의 배열
     */
   	public static String[] token(String str, String tokenStr)
   	{
   		StringTokenizer stringTokenizer	= new StringTokenizer(str, tokenStr);
   		String tokenString[]			= new String[stringTokenizer.countTokens()];

   		int i = 0;
   		while(stringTokenizer.hasMoreElements())
   		{
   			tokenString[i++] = stringTokenizer.nextToken();
   		}
   		return tokenString;
   	}

     /**
     * 숫자값에 1000단위 ','를 넣는다.
     *
     * @param     str 체크할 값
     * @return    ','가 넣은 값
     */
   	public static String commaIn(String str)
   	{
   		String strCommaIn	= "";
   		String strTmp = "";
   		int pos = str.indexOf(".");
   		
   		if(-1 < pos){
   			strTmp = str.substring(pos, pos+3);
   		}
   		if ( str!=null &&  ! str.equals("") &&! str.equals("null") )
   		{
   			// Long 타입 또는 Double 타입으로 변환해야함.
   			// 일단 Double로 하고.. 나중에 case 나누자.
   			strCommaIn	= NumberFormat.getInstance().format( Double.parseDouble(str) );
   		}
   		
   		strCommaIn = strCommaIn + "" + strTmp;
   		return strCommaIn;
   	}

     /**
     * 숫자값에 1000단위 ','를 넣은 값에서 ','를 제거한다.
     *
     * @param     str 체크할 값
     * @return    ','가 제거된 값
     */
   	public static String commaOut(String str)
   	{
   		String strCommaOut	= "";
   		if ( ! str.equals("") )
   		{
   			StringTokenizer strTokenizer	= new StringTokenizer(str, ",");
   			StringBuffer	strBuffer		= new StringBuffer();

   			while(strTokenizer.hasMoreTokens())
   			{
   				strBuffer.append(strTokenizer.nextToken());
   			}
   			strCommaOut	= strBuffer.toString();
   		}

   		return strCommaOut;
   	}

    /**
     * Removes white space from left ends of the string argument.
     * @param     s 원본문자열
     * @return    a left trimed string, with white space removed from the front.
     */
       public static String ltrim(String s)
       {
           if(s == null)
               return "";
           for(int i = 0; i < s.length(); i++)
               if(" \t\n\r\f".indexOf(s.charAt(i)) == -1)
                   return s.substring(i);

           return "";
       }

     /**
     * Removes white space from left ends of the string argument.
     * @param     s 원본문자열
     * @return    a right trimed string, with white space removed from the end.
     */
       public static String rtrim(String s)
       {
           if(s == null || s.equals(""))
               return "";
           for(int i = s.length() - 1; i >= 0; i--)
               if(" \t\n\r\f".indexOf(s.charAt(i)) == -1)
                   return s.substring(0, i);

           return "";
       }
       
       /**
        * Returns value argument, left-padded to length padLen argument with the sequence of character in padChar argument. If the value argument is null, value argument think of empty string ("").
        * @param     s   String value.
        * @param     i - the total length of the return value.
        * @param     c - padded character.
        * @return    left padded string
        */
          public static String lpad(String s, int i, char c)
          {
              if(s == null)
                  s = "";
              for(; s.length() < i; s = c + s);
              return s;
          }

          public static String lpad(short word0, int i, char c)
          {
              return lpad(String.valueOf(word0), i, c);
          }

          public static String lpad(int i, int j, char c)
          {
              return lpad(String.valueOf(i), j, c);
          }

          public static String lpad(long l, int i, char c)
          {
              return lpad(String.valueOf(l), i, c);
          }

          public static String lpad(float f, int i, char c)
          {
              return lpad(String.valueOf(f), i, c);
          }

          public static String lpad(double d, int i, char c)
          {
              return lpad(String.valueOf(d), i, c);
          }

        /**
        * Returns value argument, right-padded to length padLen argument with the sequence of character in padChar argument.
        * @param     s a double value.
        * @param     i - the total length of the return value.
        * @param     c - padded character.
        * @return    right  padded string
        */
          public static String rpad(String s, int i, char c)
          {
              if(s == null)
                  s = "";
              for(; s.length() < i; s = s + c);
              return s;
          }

          public static String rpad(short word0, int i, char c)
          {
              return rpad(String.valueOf(word0), i, c);
          }

          public static String rpad(int i, int j, char c)
          {
              return rpad(String.valueOf(i), j, c);
          }

          public static String rpad(long l, int i, char c)
          {
              return rpad(String.valueOf(l), i, c);
          }

          public static String rpad(float f, int i, char c)
          {
              return rpad(String.valueOf(f), i, c);
          }

          public static String rpad(double d, int i, char c)
          {
              return rpad(String.valueOf(d), i, c);
          }

      	/**
      	내용중 HTML 툭수기호인 문자를 HTML 특수기호 형식으로 변환합니다.
      	htmlstr		바꿀 대상인 문자열
      	return		바뀐 결과
      	PHP의 htmlspecialchars와 유사한 결과를 반환합니다.
      */
      	public static String convertHtmlTochars(String htmlstr)
      	{
      		String convert = new String();
      		convert = replace(htmlstr, "<", "&lt;");
      		convert = replace(convert, ">", "&gt;");
      		convert = replace(convert, "\"", "&quot;");
      		convert = replace(convert, "&nbsp;", "&amp;nbsp;");
      		return convert;
      	}
      	
      	public static String convertHtmlTochars2(String htmlstr)
      	{
      		String convert = new String();
      		convert = replace(htmlstr, "<", "＜");
      		convert = replace(convert, ">", "＞");
      		convert = replace(convert, "\"", "&quot;");
      		convert = replace(convert, "&nbsp;", "&amp;nbsp;");
      		return convert;
      	}
      	
      	/**
      	내용중 HTML 툭수기호인 문자를 HTML 특수기호 형식으로 변환합니다.
      	htmlstr		바꿀 대상인 문자열
      	return		바뀐 결과
      	PHP의 htmlspecialchars와 유사한 결과를 반환합니다.
      */
      	public static String convertCharsToHtml(String htmlstr)
      	{
      		String convert = new String();

      		while(convert.indexOf(System.getProperty("line.separator")) != -1) {
      			convert = replace(htmlstr, System.getProperty("line.separator"), "*");
          	}
      		convert = replace(htmlstr, "&lt;", "<");
      		convert = replace(convert, "&gt;", ">");
      		convert = replace(convert, "&quot;", "'");
      		convert = replace(convert, "&amp;nbsp;", "&nbsp;");
      		return convert;
      	}
      	
      	/**
      	내용중 특수기호인 문자를 제거합니다.
      	str		바꿀 대상인 문자열
      	return		바뀐 결과
       */
      	public static String StringReplace(String str)
      	{
//      	    int str_length = str.length();
//      	    String strlistchar   = "";
      	    String str_imsi   = ""; 
      	    String[] filter_word = {"","\\.","\\?","\\/","\\~","\\!","\\@","\\#","\\$","\\%","\\^","\\&","\\*","\\(","\\)","\\_","\\+","\\=","\\|","\\\\","\\}","\\]","\\{","\\[","\\\"","\\'","\\:","\\;","\\<","\\,","\\>","\\.","\\?","\\/"};

      	    for(int i=0;i<filter_word.length;i++){
      	        str_imsi = str.replaceAll(filter_word[i],"");
      	        str = str_imsi;
      	    }

      	    return str;     		
      	}

      	/**
      	내용중 특수기호인 문자를 두번 입력처리한다.
      	신성우 추가 2014/02/12 탈출문자 방지처리
      	str		바꿀 대상인 문자열
      	return		바뀐 결과
       */
      	public static String StringSlashReplace(String str)
      	{
//      	    int str_length = str.length();
//      	    String strlistchar   = "";
      	    String str_imsi   = ""; 
      	    String[] filter_word = {"'","\""};

      	    for(int i=0;i<filter_word.length;i++){
      	        str_imsi = str.replaceAll(filter_word[i], "\\\\" + filter_word[i]);
      	        str = str_imsi;
      	    }

      	    return str;     		
      	}
      	
      	/**
      	나모에서 작성한 내용중 HTML 툭수기호인 문자를 2Byte 특수기호 형식으로 변환합니다.
      	(나모에서 자동생성된 태그는 그대로 유지하고, 사용자가 작성한 태그에 대해 변환)
      	htmlstr		바꿀 대상인 문자열
      	return		바뀐 결과
      	PHP의 htmlspecialchars와 유사한 결과를 반환합니다.
      */
      	public static String convertNamoCharsToHtml(String htmlstr)
      	{
      		String convert = new String();

      		while(convert.indexOf(System.getProperty("line.separator")) != -1) {
      			convert = replace(htmlstr, System.getProperty("line.separator"), "*");
          	}
      		convert = replace(htmlstr, "&lt;", "＜");
      		convert = replace(convert, "&gt;", "＞");
      		return convert;
      	}
      	/**
      	 * 계약관리시스템 나모에디터 내용- 영문,숫자만 입력시 공백입력시 관련 html 태그가 View 에서 보여짐
      	 * @param htmlstr
      	 * @return
      	 */
      	public static String convertNamoReplaceAll(String htmlstr)
      	{
      		String convert = new String();

      		//while(convert.indexOf(System.getProperty("line.separator")) != -1) {
      		//	convert = replace(htmlstr, System.getProperty("line.separator"), "*");
          	//}      		
      		convert=htmlstr.replaceAll("＜", "<").replaceAll("＞", ">").replaceAll("&amp;nbsp;","");
      		
      		return convert;
      	}
      	/**
      	내용중 개행문자를 HTML <BR> 로 변환합니다.
      	htmlstr		바꿀 대상인 문자열
      	return		바뀐 결과
      */
      	public static String convertEnterToBR(String htmlstr)
      	{
      		String convert = new String();

      		//while(convert.indexOf(System.getProperty("line.separator")) != -1) {
      			//convert = replace(htmlstr, System.getProperty("line.separator"), "<BR>");
          	//}
      		
      		// 2012.03.02 개행문자 변환 수정 modified by hanjihoon
      		htmlstr = replace(htmlstr, "\r", "");
      		convert = replace(replace(htmlstr, "\n", "<BR>"), System.getProperty("line.separator"), "<BR>");

      		// 2013.10.08 스페이스 치환 추가  modified by 홍성현
      		convert = convert.replaceAll("\u0020","&nbsp;");
      		return convert;
      	}
      	
      	/**
      	내용중  HTML <BR>을 개행문자로 변환합니다.
      	htmlstr		바꿀 대상인 문자열
      	return		바뀐 결과
      */
      	public static String convertBRToEnter(String htmlstr)
      	{
      		String convert = new String();
      		
      		convert = replace(htmlstr, "<BR>", "\n");
      		
      		return convert;
      	}
      	
        /**
         * HTML 태그 제거
      	 * @param htmlstr
      	 * @return String
         */
      	public static String getText(String content) {
    		Pattern SCRIPTS = Pattern.compile("<(no)?script[^>]*>.*?</(no)?script>",Pattern.DOTALL);
    		Pattern STYLE = Pattern.compile("<style[^>]*>.*</style>",Pattern.DOTALL);
    		Pattern TAGS = Pattern.compile("<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>");
    		Pattern nTAGS = Pattern.compile("<\\w+wws+[^<]*\\s*>");
    		Pattern ENTITY_REFS = Pattern.compile("&[^;]+;");
    		Pattern WHITESPACE = Pattern.compile("\\s\\s+");;
    		
    		Matcher m;
    		
    		m = SCRIPTS.matcher(content);
    		content = m.replaceAll("");
    		m = STYLE.matcher(content);
    		content = m.replaceAll("");
    		m = TAGS.matcher(content);
    		content = m.replaceAll("");
    		m = nTAGS.matcher(content);
    		content = m.replaceAll("");
    		m = ENTITY_REFS.matcher(content);
    		content = m.replaceAll("");
    		m = WHITESPACE.matcher(content);
    		content = m.replaceAll("");
    		
    		return content;
    	}
      	
      	/*
      	 * 내용중에 script로 사용할 문구가 있는지 여부를 체크하여 리턴함.
      	 * */
      	public static boolean checkScript(String content) {
      		if(content == null) return true;
      		
      		String originString = content;
      		String result = content;
      		String upperStr = content.toUpperCase();
      		boolean bret = true;
      		
      		if(upperStr.indexOf("JAVASCRIPT") != -1){
				result = replace(upperStr,"JAVASCRIPT","X_JAVASCRIPT");
			}

			if(upperStr.indexOf("JSCRIPT") != -1){
				result = replace(upperStr,"JSCRIPT","X_JSCRIPT");
			}

			if(upperStr.indexOf("VBSCRIPT") != -1){
				result = replace(upperStr,"VBSCRIPT","X_VBSCRIPT");
			}

			if(upperStr.indexOf("IFRAME") != -1){
				result = replace(upperStr,"IFRAME","X_IFRAME");
			}

			if(upperStr.indexOf("FRAME") != -1){
				result = replace(upperStr,"FRAME","X_FRAME");
			}

			if(upperStr.indexOf("EXPRESSION") != -1){
				result = replace(upperStr,"EXPRESSION","X_EXPRESSION");
			}

			if(upperStr.indexOf("SCRIPT") != -1){
				result = replace(upperStr,"SCRIPT","X_SCRIPT");
			}

			if(upperStr.indexOf("ALERT") != -1){
				result = replace(upperStr,"ALERT","X_ALERT");
			}

			if(upperStr.indexOf("&#") != -1){
				result = replace(upperStr,"&#","");
			}
	
			if(upperStr.indexOf("ONMOUSE") != -1){
				result = replace(upperStr,"ONMOUSE","X_ONMOUSE");
			}
		
            if(upperStr.indexOf("ONCLICK") != -1){
				result = replace(upperStr,"ONCLICK","X_ONCLICK");
			}
			
            if(upperStr.indexOf("ONSUBMIT") != -1){
				result = replace(upperStr,"ONSUBMIT","X_ONSUBMIT");
			}
			
            if(upperStr.indexOf("OBJECT") != -1){
				result = replace(upperStr,"OBJECT","X_OBJECT");
			}
			
            if(upperStr.indexOf("APPLET") != -1){
				result = replace(upperStr,"APPLET","X_APPLET");
			}
			
            if(upperStr.indexOf("COOKIE") != -1){
				result = replace(upperStr,"COOKIE","X_COOKIE");
			}
			
            if(upperStr.indexOf("LOCATION") != -1){
				result = replace(upperStr,"LOCATION","X_LOCATION");
			}
			
			if(!originString.equals(result)){
				bret = false;
			}
			
      		return bret;
      	}
}