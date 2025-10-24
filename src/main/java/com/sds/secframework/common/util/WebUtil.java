package com.sds.secframework.common.util;

import java.util.*;

/**
 * 웹(JSP)에서 사용하는 공통 유틸정의
 * 
 */
public final class WebUtil
{
	/**
	* NEW-LINE 을 BR 태그로 변환한다 <BR>
	*
	* @param s 입력문자열
	* @return 변환된 문자열
	*/

	public static String nl2br(String s)
	{
		int i = s.length();
		String s1 = "";
		for (int j = 0; j < i; j++)
		{
			char c = s.charAt(j);
			if (c == '\r')
			{
				if (j < i - 1)
					if (s.charAt(j + 1) == '\n')
						j++;
				s1 = s1 + "<BR>";
			}
			else if (c == '\n')
			{
				if (j < i - 1)
					if (s.charAt(j + 1) == '\r')
						j++;
				s1 = s1 + "<BR>";
			}
			else
			{
				s1 = s1 + c;
			}
		}

		return s1;
	}

	/**
	* BR 태그를 New Line으로 변환한다 <br>
	*
	* @param str 입력문자열
	* @return 변환된 문자열
	*/

	public static String br2nl(String str)
	{
		StringTokenizer strToken = new StringTokenizer(str, "<BR>");
		StringBuffer strBuf = new StringBuffer();
		String strTmp = null;

		while (strToken.hasMoreTokens())
		{
			strTmp = strToken.nextToken();
			strBuf.append(strTmp);
			strBuf.append("\n");
		}

		return strBuf.toString();
	}

	/**
	* BR 태그를 SPACE 로 변환한다 <br>
	*
	* @param str 입력문자열
	* @return 변환된 문자열
	*/

	public static String br2space(String str)
	{
		StringTokenizer strToken = new StringTokenizer(str, "<BR>");
		StringBuffer strBuf = new StringBuffer();
		String strTmp = null;

		while (strToken.hasMoreTokens())
		{
			strTmp = strToken.nextToken();
			strBuf.append(strTmp);
			strBuf.append(" ");
		}

		return strBuf.toString();
	}

	/**
	* 두 코드를 비교하여 일치할 경우 checked 스트링을 리턴 <BR>
	* 레디오박스 등에서 사용한다 <BR>
	*
	* @param code01 원본문자열
	* @param code02 비교대상문자열
	* @return 일치할 경우 checked, 아닐경우 공백문자열을 리턴한다
	*/

	public static String chkRadioType(String code01, String code02)
	{
		if (StringUtil.isNull(code01) || StringUtil.isNull(code02))
			return "";

		if (code01.equals(code02))
			return "checked";
		else
			return "";
	}

	/**
	* 두 코드를 비교하여 일치할 경우 selected 스트링을 리턴 <BR>
	* 체크박스 등에서 사용한다 <BR>
	*
	* @param code01 원본문자열
	* @param code02 비교대상문자열
	* @return 일치할 경우 checked, 아닐경우 공백문자열을 리턴한다
	*/

	public static String chkSelectType(String code01, String code02)
	{
		if (StringUtil.isNull(code01) || StringUtil.isNull(code02))
			return "";

		if (code01.equals(code02))
			return "selected";
		else
			return "";
	}
    
    /**
     * html 태그를 변환함
     * @param p_str 원본 문자열
     * @return 변환된 html
     */
    public static String htmlToText(String p_str)
    {
        if ( p_str == null )
        {
            p_str = "";
        }
        else
        {
            p_str = p_str.trim();
            p_str = StringUtil.replace(p_str,"&","&amp;");
            p_str = StringUtil.replace(p_str,"<","&lt;");
            p_str = StringUtil.replace(p_str,">","&gt;");
            p_str = StringUtil.replace(p_str,"\"","&quot;");
            p_str = StringUtil.replace(p_str,"'","&#39;");
            p_str = StringUtil.replace(p_str,"\"","&#34;");
        }

        return p_str;
    }

    /**
     * replaceToHtml로 변환한 문자(<,>,",')를 원위치함.
     *
     * @param     szDetail : 원본문자열
     * @return    변경된 문자열
     */
     public static String textToHtml(String szDetail){


   	return szDetail.replaceAll("&lt;","<").replaceAll("&gt;", ">").replaceAll("&quot;","\"").replaceAll("&#39;","\'").replaceAll("&amp;","&");


     }

}