package com.sec.clm.search.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * IDOLServer text를 만들기 위해 제공하는 Util
 * text=쿼리를 만들때 사용한다.
 * 
 * @author KimJaeJeong <mailto:intelo@3-ware.co.kr/>
 *
 */
public class IDOLQueryParseUtil {
	
	private static String DELI_FIELD_IDOL = ":";
	public static String OPER_AND = " AND ";
	public static String OPER_OR = " OR ";
	private static String OPER_BRACKET_OPEN = "(";
	private static String OPER_BRACKET_CLOSE = ")";
	
	/**
	 * 필드별 검색시 사용한다. 필드별 검색에 대한 text를 만들어 준다.
	 * 공백을 포함한 모든 검색어를 통째로 넣어주면 알아서 만들어준다. 결과내 재검색시에 사용할 수 있다. 
	 * ex) 검색어가 a b c이고 대상 필드가 ITEM_NAME이면 
	 *     a:ITEM_NAME AND b:ITEM_NAME AND c:ITEM_NAME을 리턴한다.
	 * 
	 * @param queryText 검색어 (공백을 포함한 모든 검색어를 통째로 넣어주면 됨.)
	 * @param idolField 검색대상 필드
	 * @param operator 연산자(AND 또는 OR)
	 * @return
	 */
	public static String parse(String queryText, String idolField, String operator){
		String result = "";
		HashMap<String,String> map = StringUtils.stringToMapTokenByKillDuplicate(queryText, StringUtils.TOKEN_SPACE);
		
		Set<String> set = map.keySet();
		Iterator<String> itr = set.iterator();
		int cnt = 0;
		while(itr.hasNext()){
			cnt++;
			if(cnt == 1){
				result = OPER_BRACKET_OPEN+itr.next()+OPER_BRACKET_CLOSE+DELI_FIELD_IDOL+idolField;
			}else{
				result += operator+OPER_BRACKET_OPEN+itr.next()+OPER_BRACKET_CLOSE+DELI_FIELD_IDOL+idolField;
			}			
		}
		
		return OPER_BRACKET_OPEN+result+OPER_BRACKET_CLOSE;
	}
	
	/**
	 * 대상쿼리에 querytext 단어를 추가한다.(결과내 재검색시 또는 여러 필드를 대상으로 한 검색시 사용한다.)
	 * ex) 기존 검색쿼리가 abc:ITEM_NAME이고, 새로운 검색이 pvc를 SPEC에서 검색하는 것이라면
	 *    abc:ITEM_NAME AND pvc:SPEC을 리턴한다.
	 * 
	 * @param target
	 * @param queryText
	 * @param idolFieldName
	 * @return
	 */
	public static String putFieldOperator(String target, String queryText, String idolFieldName){
		if(target == null){
			target = queryText+DELI_FIELD_IDOL+idolFieldName;
		}
		else{
			target = target+OPER_AND+queryText+DELI_FIELD_IDOL+idolFieldName;
		}
		return target;
	}
	
	/**
	 * 주어진 String 앞뒤로 "()"를 붙여준다.
	 * 우선순위 연산자 같은 개념임.
	 * 
	 * @param str
	 * @return
	 */
	public static String putBracket(String str){
		return OPER_BRACKET_OPEN+str+OPER_BRACKET_CLOSE;
	}
	
	/**
	 * src와 target 사이에 주어진 연산자를 삽입한다.
	 * 
	 * @param target 대상이 되는 String 
	 * @param src 원본 String
	 * @param operator 연산자
	 * @return
	 */
	public static String putOperator(String target, String src, String operator){
		if(target == null){
			target = src;
		}
		else{
			target = target + operator+ src;
		}
		return target;
	}
}
