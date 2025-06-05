package com.sds.secframework.common.util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Secframework 에서 사용할 utility class
 *
 */
public class SecframeworkUtil {
	
	/**
	 * sql 쿼리에 정의된 ? 혹은 : 을 list 에 담겨져 넘어온 parameter 로 대체 한다. 
	 * @param sql 쿼리문
	 * @param params 파라미터
	 * @return 완성된 쿼리 문장
	 */
	public static String getQueryString(String sql, List params) {
		if (params == null) return sql;
		if (params.size() == 0) return sql;

		List parameterValues = new ArrayList();
		
		for (int i = 0; i < params.size(); i++) {
			Object obj = params.get(i);
			if (obj instanceof String || obj instanceof Date) {
				if (obj == null) {
					parameterValues.add("''");
				}
				else {
					String s = obj.toString().replaceAll("'", "''");
					parameterValues.add("'" + s + "'");
				}
			}
			else {
				if (obj == null) {
					parameterValues.add("");
				}
				else {
					parameterValues.add(obj.toString());
				}
			}
		}
		
		int paramSize = parameterValues.size();
		int paramIndex = 0;
 
		/**
		 * 쿼리문에 ?, : 처리
		 */
		StringBuffer sf = new StringBuffer();
		boolean startSingleQutation = false;
		boolean startColon = false;
		for (int i = 0; i < sql.length(); i++) {
			String s = sql.substring(i, i + 1);
			if (s.equals("'")) {
				if (startSingleQutation) startSingleQutation = false;
				else startSingleQutation = true;
			}

			if (startSingleQutation) {
				sf.append(s);
			}
			else {
				if (s.equals("?")) {
					startColon = false;
					if (paramIndex < paramSize) {
						sf.append((String) parameterValues.get(paramIndex++));
					}
					else {
						sf.append(sql.substring(i));
						break;
					}
				}
				else if (s.equals(":")) {
					startColon = true;
					if (paramIndex < paramSize) {
						sf.append((String) parameterValues.get(paramIndex++));
					}
					else {
						sf.append(sql.substring(i));
						break;
					}
				}
				else {
					if (startColon) {
						if ((s.compareTo("A") < 0 || s.compareTo("Z") > 0) && (s.compareTo("a") < 0 || s.compareTo("z") > 0) && (s.compareTo("0") < 0 || s.compareTo("9") > 0) && !s.equals("_")) {

							startColon = false;
							sf.append(s);
						}
					}
					else {
						sf.append(s);
					}
				}
			}
		}
		return sf.toString();
	}
}
