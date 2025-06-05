package com.sds.secframework.common.util;

import java.io.Reader;
import java.security.MessageDigest;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import samsung.esb.common.vo.ESBAuthVO;

/**
 * <P>
 * Single ESB Utility Class
 * </P>
 * 
 * 싱글 ESB연계시 필요 Utility 클래스<BR>
 * Default 설정파일 : SingleIF.properties
 * 
 * @version com.sds.secframework.singleIF V1.0 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */
public class EsbUtil {

	public EsbUtil() {
	}

	/**
	 * ESB연계시 지정된 ID, PW정보를 AUTH VO로 Wrap해주는 Utility성 메소드
	 *
	 * @param esbId:ESB연계아이디,
	 *            esbPw:ESB연계패스워드
	 * @return ESBAuthVO ESB연계 정보 Value Object
	 */
	public static ESBAuthVO getESBAuthVO(String esbId, String esbPw) {
		ESBAuthVO esbAuthVo = new ESBAuthVO();
		esbAuthVo.setCID(esbId);
		esbAuthVo.setCPW(esbPw);
		return esbAuthVo;
	}

	/**
	 * MIS ID 제너레이터
	 *
	 * @return String
	 */
	public static String generateMisId() {
		String RNDValue = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String head = EsbUtil.getTimeByLocalTimeZone("yyyyMMddHHmmss") + "_";
		Random random = new Random(System.currentTimeMillis());
		StringBuffer sTemp = new StringBuffer(head);
		int nMAX_LNG = 33 - head.length();
		for (int ii = 1; ii < nMAX_LNG; ii++) {
			int nRnd = random.nextInt(31);
			sTemp.append(RNDValue.substring(nRnd, nRnd + 1));
		}
		return sTemp.toString();
	}

	/**
	 * MIS ID 제너레이터
	 *
	 * @return String
	 */
	public static String generateMisId(String gbn) {

		if (gbn == null || gbn.length() < 1 || gbn.length() > 5 || !isAlphaNumeric(gbn)) {
			return "ERROR";
		}
		String moduleType = gbn;
		String RNDValue = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String head = EsbUtil.getTimeByLocalTimeZone("yyyyMMddHHmmss");
		Random random = new Random(System.currentTimeMillis());
		StringBuffer sTemp = new StringBuffer(moduleType);
		sTemp.append(head);
		int nMAX_LNG = 33 - (moduleType.length() + head.length());
		for (int ii = 1; ii < nMAX_LNG; ii++) {
			int nRnd = random.nextInt(31);
			sTemp.append(RNDValue.substring(nRnd, nRnd + 1));
		}
		return sTemp.toString();
	}

	/**
	 * MIS ID 제너레이터 참고 : for문과 같이 하나의 인스턴스에서 거의 동시에 반복적으로 호출하는 경우 중복키가 발생하므로
	 * for문의 loop번호를 넘겨받아서 유일한 값이 되도록 설정함.
	 * 
	 * @return String
	 */
	public static String generateMisId(String gbn, int iLoop) {

		if (gbn == null || gbn.length() < 1 || gbn.length() > 5 || !EsbUtil.isAlphaNumeric(gbn)) {
			return "ERROR";
		}
		String moduleType = gbn;
		String RNDValue = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String head = EsbUtil.getTimeByLocalTimeZone("yyyyMMddHHmmss") + StringUtil.paddingZero(String.valueOf(iLoop), 4);
		Random random = new Random(System.currentTimeMillis());
		StringBuffer sTemp = new StringBuffer(moduleType);
		sTemp.append(head);
		int nMAX_LNG = 33 - (moduleType.length() + head.length());
		for (int ii = 1; ii < nMAX_LNG; ii++) {
			int nRnd = random.nextInt(31);
			sTemp.append(RNDValue.substring(nRnd, nRnd + 1));
		}
		return sTemp.toString();
	}

	public static boolean isAlphaNumeric(String input) {
		String sAlphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		int iLen = input.length();

		for (int i = 0; i < iLen; i++) {
			if (sAlphabet.indexOf(input.substring(i, i + 1)) < 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * CLOB 데이타를 String으로 변환
	 *
	 * @param clob
	 *            CLOB 타입의 데이타
	 * @return String
	 */
	public static String getStringFromCLOB(Clob clob) throws Exception {
		String result = "";
		StringBuffer output = new StringBuffer();

		Reader input = clob.getCharacterStream();
		char[] buffer = new char[1024];
		int byteRead;
		while ((byteRead = input.read(buffer, 0, 1024)) != -1) {
			output.append(buffer, 0, byteRead);
		}
		input.close();
		result = output.toString();

		return result;
	}

	/**
	 * 정해진 포맷으로 년월일시분초 받아오기
	 *
	 * @param format
	 *            날짜포맷정보
	 * @return String
	 *
	 */
	public static String getTime(String format) {
		if (format == null || format.equals("") == true)
			format = "yyyyMMddHHmmss";

		TimeZone tz = TimeZone.getDefault();
		tz.setRawOffset((60 * 60 * 1000) * 9);
		TimeZone.setDefault(tz);
		Calendar cal = Calendar.getInstance(tz);
		Date date = cal.getTime();
		SimpleDateFormat formater = new SimpleDateFormat(format, Locale.getDefault());
		String timestamp = formater.format(date);

		return timestamp;
	}

	/**
	 * This method always return current time with format by GMT+0.
	 * 
	 * @param format
	 * @return
	 */
	public static String getTimeByGMT(String format) {
		if (format == null || format.equals("") == true)
			format = "yyyyMMddHHmmss";

		TimeZone tz = TimeZone.getDefault();
		tz.setRawOffset(-1 * (tz.getOffset(System.currentTimeMillis())));
		TimeZone.setDefault(tz);
		Calendar cal = Calendar.getInstance(tz);
		Date date = cal.getTime();
		SimpleDateFormat formater = new SimpleDateFormat(format, Locale.getDefault());
		String timestamp = formater.format(date);
		return timestamp;
	}

	/**
	 * This method always return current time with format by GMT+0.
	 * 
	 * @param format
	 * @return
	 */
	public static String getTimeByLocalTimeZone(String format) {
		if (format == null || format.equals("") == true)
			format = "yyyyMMddHHmmss";

		 
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		SimpleDateFormat formater = new SimpleDateFormat(format, Locale.getDefault());
		String timestamp = formater.format(date);
		return timestamp;
	}
	
	/**
	 * 주어진 날짜를 yyyyMMddHHmmss 형식으로 돌려줌 <BR>
	 *
	 * @param longData
	 *            long형 date
	 * @return yyyyMMddHHmmss 형식의 14자리 날짜
	 */

	public static String getDate(long longData) {
		if (longData != 0) {
			Date date = new Date(longData);
			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMddHHmmss");
			return simpledateformat.format(date);
		} else {
			return "0";
		}
	}

	/**
	 * 주어진 날짜를 yyyyMMddHHmmss 형식으로 돌려줌 <BR>
	 *
	 * @param longData
	 *            long형 date
	 * @return yyyyMMddHHmmss 형식의 14자리 날짜
	 */

	public static String getDate(Long inputData) {

		long longData = inputData.longValue();
		if (longData != 0) {
			Date date = new Date(longData);
			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMddHHmmss");
			return simpledateformat.format(date);
		} else {
			return "0";
		}
	}

	/**
	 * 첫번째 변수를 체크하여 NULL 또는 ""값이면 두번째 변수를 리턴하고, 아니면 그대로 리턴한다.
	 *
	 * @param str
	 *            체크할 값
	 * @param str1
	 *            ""일 경우 리턴할 값
	 * @return str : ""일 경우 str1, 아닐경우 str
	 */
	public static String bvl(String str, String str1) {
		return str != null && !str.equals("") ? str : str1;
	}

	/**
	 * 사용자 패스워드를 단방향 해시 MD5 - BASE64 Encoding 한다. 메일발송 취소시 사용자 입력데이타(비밀번호) 값 변환시
	 * 사용.
	 * 
	 * @param input
	 *            입력값(평문)
	 * @return MD5 - BASE64 Encoding 값
	 */
	public static synchronized String getMD5_Base64(String input) {

		MessageDigest digest = null;

		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (digest == null)
			return input;
		try {
			digest.update(input.getBytes("UTF-8"));
		} catch (java.io.UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		byte[] rawData = digest.digest();
		
		sun.misc.BASE64Encoder bencoder = new sun.misc.BASE64Encoder();
		return bencoder.encode(rawData);
		//return java.util.Base64.getEncoder().encodeToString(rawData);
	}
}