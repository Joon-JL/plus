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
    public static String nl2br(String s) {
        // 1. 방어 코드: 입력값이 null이거나 비어있으면 즉시 처리 종료
        if (s == null || s.isEmpty()) {
            return "";
        }

        int len = s.length();

        // 2. 초기 버퍼 크기를 입력 스트링 대비 약간의 여유(공간 확장)를 두어 할당 (배열 재할당 오버헤드 방지)
        StringBuilder sb = new StringBuilder(len + 16);

        for (int j = 0; j < len; j++) {
            char c = s.charAt(j);

            if (c == '\r') {
                // \r\n (윈도우 개행문자) 처리: 다음 글자가 \n 이면 인덱스 건너뜀
                if (j < len - 1 && s.charAt(j + 1) == '\n') {
                    j++;
                }
                sb.append("<BR>");
            }
            else if (c == '\n') {
                // \n\r 처리: 다음 글자가 \r 이면 인덱스 건너뜀
                if (j < len - 1 && s.charAt(j + 1) == '\r') {
                    j++;
                }
                sb.append("<BR>");
            }
            else {
                // 3. 일반 문자는 임시 객체 생성 없이 버퍼에 직접 추가
                sb.append(c);
            }
        }

        return sb.toString();
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