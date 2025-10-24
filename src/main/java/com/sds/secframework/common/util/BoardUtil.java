package com.sds.secframework.common.util;

import java.util.List;
import java.util.Map;

import com.sds.secframework.board.dto.BoardMngForm;

public class BoardUtil {
	
	/**
	 * 게시판명을 해당 언어타입에 맞는 필드에 세팅
	 * @param form BoardMngForm
	 * @param boardNmList 게시판명 리스트
	 * @throws Exception
	 */
	static public void setBoardNm(BoardMngForm form, List boardNmList) throws Exception {
		String fieldName = "board_nm_" ;
		
		for(int i=0; i<boardNmList.size(); i++) {
			Map map = (Map)boardNmList.get(i) ;
			String boardNm = (String)map.get("board_nm") ;
			String languageType = (String)map.get("language_type") ;
			
			// 게시판명을 언어타입에 맞는 필드에 세팅
			ObjectCopyUtil.setFieldValue(form, fieldName+languageType, boardNm, String.class) ;
		}
		
	}
	
	/**
	 * 해당 언어타입에 맞는 게시판명을 board_nm 에 세팅
	 * @param form BoardMngForm
	 * @param boardNmList 게시판명 리스트
	 * @param languageType 언어 타입(ko, en, cn...)
	 * @throws Exception
	 */
	static public void setBoardNm(BoardMngForm form, List boardNmList, String languageType) throws Exception {
	
		for(int i=0; i<boardNmList.size(); i++) {
			Map map = (Map)boardNmList.get(i) ;
			String boardNm = (String)map.get("board_nm") ;
			String lang = (String)map.get("language_type") ;
			
			if(languageType.equals(lang)){
				form.setBoard_nm(boardNm) ;
				break ;
			}
		}
		
	}
	
	/**
	 * 페이지당 조회 Row 수를 select 의 option으로 만들어서 리턴
	 * 
	 * @param rowCnt Row수(10,20,30 처럼 , 로 구분)
	 * @param selectedValue 기본 선택 값
	 * @param unitMsg 단위 문구(ex. 개씩 보기)
	 * @return
	 */
	static public String getRowCntCombo(String rowCnt, int selectedValue, String unitMsg) {
		StringBuffer sb = new StringBuffer() ;
		String[] rowCntArray = rowCnt.split(",") ;
		for(int i=0; i<rowCntArray.length; i++) {
			sb.append("<option value=\"") ;
			sb.append(rowCntArray[i].trim()) ;
			sb.append("\"") ;
			if(StringUtil.str2int( rowCntArray[i])==selectedValue){
				sb.append(" selected") ;
			}
			sb.append(">") ;
			sb.append(rowCntArray[i].trim()) ;
			if(unitMsg!=null){
				sb.append(unitMsg) ;
			}
			sb.append("</option>") ;
		}
		
		return sb.toString();
	}
}
