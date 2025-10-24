package com.sec.clm.search.service;

import java.util.HashMap;
import java.util.List;

import com.sec.clm.search.dto.SearchVO;

public interface SearchService {
	
	List listSearch(SearchVO vo) throws Exception;

	/**
	 * 매뉴 구분에 따라 Top 메뉴, Left 메뉴를 반환한다
	 * 
	 * @param	menuGbn  -> TOP  : Top Menu
	 * 					 -> LEFT : Left Menu
	 */
	HashMap listMenu(HashMap hm) throws Exception;
	
	/**
	 * 계약유형 코드를 가져온다.
	 */
	String getMasterCombo(String comboflag, String upCd, SearchVO vo) throws Exception;

}
