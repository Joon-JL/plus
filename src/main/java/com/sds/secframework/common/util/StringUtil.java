package com.sds.secframework.common.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 문자열과 관련된 일반적인 메쏘드를 정의한다 (SonarQube 보안/품질 표준 준수)
 */
public final class StringUtil
{
    // ReDoS 방지를 위한 possessive 및 단순화 처리된 정규식 선언
    private static final Pattern SCRIPTS = Pattern.compile("<(no)?script[^>]*+>.*?</(no)?script>", Pattern.DOTALL);
    private static final Pattern STYLE = Pattern.compile("<style[^>]*+>.*?</style>", Pattern.DOTALL);
    private static final Pattern TAGS = Pattern.compile("<[^>]*+>");
    private static final Pattern N_TAGS = Pattern.compile("<\\w+[^<]*+>");
    private static final Pattern ENTITY_REFS = Pattern.compile("&[^;]+;");
    private static final Pattern WHITESPACE = Pattern.compile("\\s\\s+");

    // 유틸리티 클래스의 인스턴스화 방지 (SonarQube 무조건 지적 사항 코드스멜 해결)
    private StringUtil() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * 주어진 길이보다 길이가 작은 문자열을 앞에 0을 붙여 패딩한다
     */
    public static String paddingZero(String str, int len)
    {
        if (str == null) return "";
        int strLen = str.length();

        if (strLen >= len) {
            return str;
        }

        int cab = len - strLen;
        StringBuilder tmp = new StringBuilder();
        for (int ii = 0; ii < cab; ii++)
        {
            tmp.append("0");
        }

        return tmp.append(str).toString();
    }

    /**
     * 첫문자를 대문자로 바꿈
     */
    public static String initCap(String str)
    {
        if (str == null || str.isEmpty()) return "";
        String second = str.substring(1);
        char h = str.charAt(0);
        if (h >= 'a' && h <= 'z')
        {
            String first = String.valueOf((char) (h - 32));
            return first + second;
        }
        else {
            return str;
        }
    }

    /**
     * 원본문자열(str)에서 캐릭터(ch)를 찾아 제거한다
     */
    public static String removeChar(String str, char ch)
    {
        return removeChar(str, String.valueOf(ch));
    }

    /**
     * 원본문자열(str)에서 문자열(ch)을 찾아 제거한다
     */
    public static String removeChar(String str, String ch)
    {
        if (str == null) return "";
        StringBuilder buff = new StringBuilder();
        StringTokenizer token = new StringTokenizer(str, ch);
        while (token.hasMoreTokens())
        {
            buff.append(token.nextToken());
        }

        return buff.toString();
    }

    /**
     * 문자열을 정수로 안전하게 변환한다 (런타임 Crash 방지)
     */
    public static int str2int(String str)
    {
        if (str == null || str.trim().isEmpty()) {
            return 0;
        }

        try {
            return Integer.parseInt(str.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * 정수를 문자열로 변환한다 (Deprecated 생성자 비효율 제거)
     */
    public static String int2Str(int i)
    {
        return String.valueOf(i);
    }

    /**
     * 널인 문자열을 스페이스("")로 치환한다
     */
    public static String null2space(String s)
    {
        if (s == null || s.isEmpty()) {
            return "";
        } else {
            return s.trim();
        }
    }

    /**
     * 널이거나 빈 문자열을 원하는 스트링으로 변환한다
     */
    public static String null2str(String org, String converted)
    {
        if (org == null || org.trim().isEmpty()) {
            return converted;
        } else {
            return org.trim();
        }
    }

    /**
     * 문자열이 널이거나 빈 공백문자열인지 CHECK 한다
     */
    public static boolean isNull(String str)
    {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 문자열에서 주어진 separator 로 쪼개어 문자배열을 생성한다.
     */
    public static String[] split(String str, String separator)
    {
        if (str == null) return new String[0];
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
     * separator 문자열 매핑 분리 분기 처리기
     */
    public static String[] split(String str, String separator, boolean useToken) {
        if (str == null) return new String[0];
        if (useToken) {
            return split(str, separator);
        }
        else {
            int index = 0;
            String[] resultStrArray = new String[search(str, separator) + 1];
            String strCheck = str;
            while(!strCheck.isEmpty()) {
                int begin = strCheck.indexOf(separator);
                if(begin == -1) {
                    resultStrArray[index] = strCheck;
                    break;
                } else {
                    int end = begin + separator.length();
                    resultStrArray[index++] = strCheck.substring(0, begin);

                    strCheck = strCheck.substring(end);
                    if(strCheck.isEmpty()){
                        resultStrArray[index] = strCheck;
                        break;
                    }
                }
            }
            return resultStrArray;
        }
    }

    /**
     * 문자열 배열을 주어진 separator 로 연결하여 문자열을 생성한다
     */
    public static String join(String[] list, String separator)
    {
        if (list == null) return "";
        StringBuilder csv = new StringBuilder();
        for (int i = 0; i < list.length; i++)
        {
            if (i > 0)
                csv.append(separator);
            csv.append(list[i]);
        }
        return csv.toString();
    }

    /**
     * Exception 객체로 에러메시지 문자열을 생성한다
     */
    public static String stackTrace(Throwable e)
    {
        if (e == null) return "";
        String str = "";
        try (ByteArrayOutputStream buff = new ByteArrayOutputStream();
             PrintWriter writer = new PrintWriter(buff, true))
        {
            e.printStackTrace(writer);
            str = buff.toString();
        }
        catch (Exception f)
        {
            // 예외 처리 추적 유지용 블록
        }

        return str;
    }

    /**
     * 원본문자열에서 뒤로부터 주어진 문자열(ch)을 찾아 제거한다
     */
    public static String removeLastChar(String str, String ch) throws Exception
    {
        if (str == null || ch == null) return "";
        int pos = str.lastIndexOf(ch);
        if (pos == -1)
            return str;
        else
            return str.substring(0, pos) + str.substring(pos + 1);
    }

    /**
     * 문자열대 문자열로 바꿔준다.
     */
    public static String replace(String line, String oldString, String newString)
    {
        if (line == null || oldString == null || newString == null || oldString.isEmpty()) return "";
        StringBuilder builder = new StringBuilder(null2space(line));
        int index = 0;
        while ((index = builder.indexOf(oldString, index)) >= 0) {
            builder.replace(index, index + oldString.length(), newString);
            index += newString.length();
        }
        return builder.toString();
    }

    public static String leftPad(String str, char add, int len)
    {
        if (str == null) return "";
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < len) {
            sb.insert(0, add);
        }
        return sb.toString();
    }

    public static String rightPad(String str, char add, int len)
    {
        if (str == null) return "";
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < len) {
            sb.append(add);
        }
        return sb.toString();
    }

    public static String leftPadB(String str, char add, int blen)
    {
        if (str == null) return "";
        StringBuilder sb = new StringBuilder(str);
        while (sb.toString().getBytes().length < blen) {
            sb.insert(0, add);
        }
        return sb.toString();
    }

    public static String rightPadB(String str, char add, int blen)
    {
        if (str == null) return "";
        StringBuilder sb = new StringBuilder(str);
        while (sb.toString().getBytes().length < blen) {
            sb.append(add);
        }
        return sb.toString();
    }

    private static int search(String strTarget, String strSearch){
        if (strTarget == null || strSearch == null || strSearch.isEmpty()) return 0;
        int result = 0;
        String strCheck = strTarget;
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

    public static String nvl(Object obj, String str)
    {
        return obj != null ? obj.toString() : str;
    }

    public static String nvl(String str, String str1)
    {
        return str != null ? str : str1;
    }

    public static String bvl(Object obj, String str)
    {
        return obj != null && !obj.toString().isEmpty() ? obj.toString() : str;
    }

    public static String bvl(String str, String str1)
    {
        return str != null && !str.isEmpty() ? str : str1;
    }

    /**
     * 싱글라인 보안 이스케이프용 비즈니스 래퍼 (XSS 대응용 추가)
     */
    public static String bvlEscaped(String str, String str1) {
        if (str != null && !str.isEmpty()) {
            return convertHtmlTochars(str.trim());
        } else {
            return str1;
        }
    }

    /**
     * 멀티라인 보안 이스케이프용 비즈니스 래퍼 (XSS 대응용 추가)
     */
    public static String bvlEscapeWithBR(String str, String defaultStr) {
        if (str == null || str.trim().isEmpty()) {
            return defaultStr;
        }
        String safeHtml = convertHtmlTochars(str.trim());
        return convertEnterToBR(safeHtml);
    }

    public static String mainBvl(String str, String str1)
    {
        if(str != null) {
            str = str.replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("'", "''")
                .replace("--", "__")
                .replace(";", "|    ")
                .replace("%0a", " ");
        }
        return str != null && !str.isEmpty() ? str : str1;
    }

    public static String[] token(String str, String tokenStr)
    {
        if (str == null) return new String[0];
        StringTokenizer stringTokenizer = new StringTokenizer(str, tokenStr);
        String[] tokenString = new String[stringTokenizer.countTokens()];

        int i = 0;
        while(stringTokenizer.hasMoreElements())
        {
            tokenString[i++] = stringTokenizer.nextToken();
        }
        return tokenString;
    }

    public static String commaIn(String str)
    {
        if (str == null || str.isEmpty() || "null".equals(str)) return "";
        String strCommaIn = "";
        String strTmp = "";
        int pos = str.indexOf(".");

        if(-1 < pos && pos + 3 <= str.length()){
            strTmp = str.substring(pos, pos + 3);
        }
        try {
            strCommaIn = NumberFormat.getInstance().format(Double.parseDouble(str));
        } catch (NumberFormatException e) {
            return str;
        }

        return strCommaIn + strTmp;
    }

    public static String commaOut(String str)
    {
        if (str == null || str.isEmpty()) return "";
        StringTokenizer strTokenizer = new StringTokenizer(str, ",");
        StringBuilder strBuffer = new StringBuilder();

        while(strTokenizer.hasMoreTokens())
        {
            strBuffer.append(strTokenizer.nextToken());
        }
        return strBuffer.toString();
    }

    public static String ltrim(String s)
    {
        if(s == null) return "";
        for(int i = 0; i < s.length(); i++)
            if(" \t\n\r\f".indexOf(s.charAt(i)) == -1)
                return s.substring(i);

        return "";
    }

    public static String rtrim(String s)
    {
        if(s == null || s.isEmpty()) return "";
        for(int i = s.length() - 1; i >= 0; i--)
            if(" \t\n\r\f".indexOf(s.charAt(i)) == -1)
                return s.substring(0, i);

        return "";
    }

    public static String lpad(String s, int i, char c)
    {
        if(s == null) s = "";
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < i) {
            sb.insert(0, c);
        }
        return sb.toString();
    }

    public static String lpad(short word0, int i, char c) { return lpad(String.valueOf(word0), i, c); }
    public static String lpad(int i, int j, char c) { return lpad(String.valueOf(i), j, c); }
    public static String lpad(long l, int i, char c) { return lpad(String.valueOf(l), i, c); }
    public static String lpad(float f, int i, char c) { return lpad(String.valueOf(f), i, c); }
    public static String lpad(double d, int i, char c) { return lpad(String.valueOf(d), i, c); }

    public static String rpad(String s, int i, char c)
    {
        if(s == null) s = "";
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < i) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static String rpad(short word0, int i, char c) { return rpad(String.valueOf(word0), i, c); }
    public static String rpad(int i, int j, char c) { return rpad(String.valueOf(i), j, c); }
    public static String rpad(long l, int i, char c) { return rpad(String.valueOf(l), i, c); }
    public static String rpad(float f, int i, char c) { return rpad(String.valueOf(f), i, c); }
    public static String rpad(double d, int i, char c) { return rpad(String.valueOf(d), i, c); }

    public static String convertHtmlTochars(String htmlstr)
    {
        if (htmlstr == null) return "";
        return htmlstr.replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("&nbsp;", "&amp;nbsp;");
    }

    public static String convertHtmlTochars2(String htmlstr)
    {
        if (htmlstr == null) return "";
        return htmlstr.replace("<", "＜")
            .replace(">", "＞")
            .replace("\"", "&quot;")
            .replace("&nbsp;", "&amp;nbsp;");
    }

    public static String convertCharsToHtml(String htmlstr)
    {
        if (htmlstr == null) return "";
        String convert = htmlstr;
        String lineSep = System.getProperty("line.separator");
        if (lineSep != null && !lineSep.isEmpty()) {
            convert = convert.replace(lineSep, "*");
        }
        return convert.replace("&lt;", "<")
            .replace("&gt;", ">")
            .replace("&quot;", "'")
            .replace("&amp;nbsp;", "&nbsp;");
    }

    public static String StringReplace(String str)
    {
        if (str == null) return "";
        String result = str;
        String[] filter_word = {"","\\.","\\?","\\/","\\~","\\!","\\@","\\#","\\$","\\%","\\^","\\&","\\*","\\(","\\)","\\_","\\+","\\=","\\|","\\\\","\\}","\\]","\\{","\\[","\\\"","\\'","\\:","\\;","\\<","\\,","\\>","\\.","\\?","\\/"};

        for (String word : filter_word) {
            if (!word.isEmpty()) {
                result = result.replaceAll(word, "");
            }
        }
        return result;
    }

    public static String StringSlashReplace(String str)
    {
        if (str == null) return "";
        String result = str;
        String[] filter_word = {"'", "\""};

        for (String word : filter_word) {
            result = result.replace(word, "\\" + word);
        }
        return result;
    }

    public static String convertNamoCharsToHtml(String htmlstr)
    {
        if (htmlstr == null) return "";
        String convert = htmlstr;
        String lineSep = System.getProperty("line.separator");
        if (lineSep != null && !lineSep.isEmpty()) {
            convert = convert.replace(lineSep, "*");
        }
        return convert.replace("&lt;", "＜")
            .replace("&gt;", "＞");
    }

    public static String convertNamoReplaceAll(String htmlstr)
    {
        if (htmlstr == null) return "";
        return htmlstr.replace("＜", "<")
            .replace("＞", ">")
            .replace("&amp;nbsp;", "");
    }

    public static String convertEnterToBR(String htmlstr)
    {
        if (htmlstr == null) return "";
        String convert = htmlstr.replace("\r", "");
        convert = convert.replace("\n", "<BR>");

        String lineSep = System.getProperty("line.separator");
        if (lineSep != null && !lineSep.isEmpty()) {
            convert = convert.replace(lineSep, "<BR>");
        }

        return convert.replace("\u0020", "&nbsp;");
    }

    public static String convertBRToEnter(String htmlstr)
    {
        if (htmlstr == null) return "";
        return htmlstr.replace("<BR>", "\n");
    }

    /**
     * HTML 태그 제거 (ReDoS 보안 취약점 전면 수정)
     */
    public static String getText(String content) {
        if (content == null) return "";

        String result = SCRIPTS.matcher(content).replaceAll("");
        result = STYLE.matcher(result).replaceAll("");
        result = TAGS.matcher(result).replaceAll("");
        result = N_TAGS.matcher(result).replaceAll("");
        result = ENTITY_REFS.matcher(result).replaceAll("");
        result = WHITESPACE.matcher(result).replaceAll("");

        return result;
    }

    /*
     * 내용중에 script로 사용할 문구가 있는지 여부를 체크하여 리턴함.
     */
    public static boolean checkScript(String content) {
        if(content == null) return true;

        String upperStr = content.toUpperCase();

        return !upperStr.contains("JAVASCRIPT") &&
            !upperStr.contains("JSCRIPT") &&
            !upperStr.contains("VBSCRIPT") &&
            !upperStr.contains("IFRAME") &&
            !upperStr.contains("FRAME") &&
            !upperStr.contains("EXPRESSION") &&
            !upperStr.contains("SCRIPT") &&
            !upperStr.contains("ALERT") &&
            !upperStr.contains("&#") &&
            !upperStr.contains("ONMOUSE") &&
            !upperStr.contains("ONCLICK") &&
            !upperStr.contains("ONSUBMIT") &&
            !upperStr.contains("OBJECT") &&
            !upperStr.contains("APPLET") &&
            !upperStr.contains("COOKIE") &&
            !upperStr.contains("LOCATION");
    }
}