package com.sec.clm.search.common.util;

import java.util.ArrayList;

public class SummaryUtils {
	
	private static String startTag = "<span class=\"search_point\">";
	private static String endTag = "</span>";
	
	
	public static String getSummary(String srcStr, ArrayList listLink, int limit){
		if(srcStr == null || listLink == null) return "";
		String strLink = "";
		for(int i = 0; i < listLink.size(); i++){
			if(i < listLink.size()-1){
				strLink = strLink+(String)listLink.get(i)+",";
			}
			else{
				strLink = strLink+(String)listLink.get(i);
			}
		}
		
		return getSummary(srcStr, strLink, limit);
	}
	
	/**
	 * 검색엔진으로 부터 조회한 문자열에 hightlight 적용 및 summary 정보를 추출하기 위한 메소드(과거 ACI를 사용한 방식)
	 * 
	 * @param srcStr  검색엔진으로 부터 조회한 문자열
	 * @param strLink 검색엔진으로 부터 조회한 match키워드 ArrayList
	 * @param limit 요약정보를 추출할 byte 크기
	 * @return
	 */
	public static String getSummary(String srcStr, String strLink, int limit) {
		
		int s_pos = -1;
		int maxLength = limit;
		
		if(srcStr == null){
			return "";
		}
		
		if(strLink!=null&&strLink.length()>0) {

			String tmpLink = "";		
			String[] arrLinks = strLink.split(",");
			
			if(arrLinks!=null) {
				for(int j=0; j<arrLinks.length; j++) {
					boolean isExist = false;
					for(int k=j+1; k<arrLinks.length; k++) {
						if(arrLinks[j].trim().equalsIgnoreCase(arrLinks[k].trim())) {
							isExist = true;
							break;
						}
					}
					if(!isExist) {
						if(tmpLink.length()>0) tmpLink += ", ";
						tmpLink += arrLinks[j];
					}
				}
				arrLinks = tmpLink.split(",");
			}

			if(arrLinks!=null) {				
				int i=0;				
				for(i=0; i<arrLinks.length; i++) {
					
					int tempPos = srcStr.toUpperCase().indexOf(arrLinks[i].trim().toUpperCase());					
					if(tempPos!=-1) {
						s_pos = tempPos;
						break;
					}
				}								
				
				if(s_pos>-1) {
					if(srcStr.length()>maxLength) {
						int start = srcStr.lastIndexOf(" ", s_pos);
						if(start==-1) start = 0;
						int end = start + maxLength;

						if( (start+maxLength)>srcStr.length() ) {						
							end = srcStr.length();
						}
						srcStr = "..."+srcStr.substring(start, end) + "...";
					}										
					
					for(i=0; i<arrLinks.length; i++) {						
						if(arrLinks[i]!=null){
							String tempLink = arrLinks[i].trim();
							if(tempLink != null && !tempLink.equals("")){
								srcStr=replaceHighlight(srcStr,arrLinks[i].trim());
							}
						}	
					}
				} else {
					if(srcStr.length()>maxLength) {
						srcStr = "..."+srcStr.substring(0, maxLength) + "...";
					}
				}
			}
		}		
		return srcStr;
	}
	
		
	public static String replaceHighlight(String str, String terms){
		String result = "";
		if(str == null) return result;
		if(terms == null) return str;
		
		String[] termArr = terms.split(",");
		if(termArr != null && termArr.length >1) {
			for(int i =0; i<termArr.length;i++){
				str = setHighlight(str,termArr[i]);
			}
			result = str;
		}
		else{
			return setHighlight(str, terms);
		}
		return result;
	}
	
	private static String setHighlight(String str, String highTerm){
		String strUpper = str.toUpperCase();
		StringBuffer sb = new StringBuffer();
		int pos = 0, pos2 = 0;
		while(( pos2 = strUpper.indexOf( highTerm, pos)) >= 0) {
			sb.append(str.substring( pos, pos2));
			sb.append(startTag);
			sb.append(str.substring( pos2, pos2 + highTerm.length()));
			sb.append(endTag);
			pos = pos2 + highTerm.length();
		}
		sb.append(str.substring( pos, str.length()));
		
	    return sb.toString();
	}
}
