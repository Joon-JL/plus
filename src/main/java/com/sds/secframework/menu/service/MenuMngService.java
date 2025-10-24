package com.sds.secframework.menu.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.menu.dto.MenuMngVO;

/**
 * 메뉴관리 Interface
 *
 */
public interface MenuMngService {

	/**
	 * 메뉴 목록 Tree 형식
	 * @param 
	 * @return 메뉴리스트 JSON
	 * @throws Exception 
	 */
	JSONArray listMenuTree(MenuMngVO vo) throws Exception;

	/**
	 * 메뉴 목록 Table 형식
	 * @param 
	 * @return 메뉴리스트 JSON
	 * @throws Exception 
	 */
	JSONArray listMenuTable(MenuMngVO vo) throws Exception;

	/**
	 * 메뉴를 등록한다.
	 * @param menuVO 메뉴VO
	 * @return
	 * @throws Exception
	 */
	JSONArray insertMenu(MenuMngVO vo) throws Exception;

	/**
	 * 메뉴정보를 수정한다.
	 * @param menuVO 메뉴VO
	 * @return
	 * @throws Exception
	 */
	int modifyMenu(MenuMngVO vo) throws Exception;

	/**
	 * 메뉴를 삭제.
	 * @param menuVO 메뉴VO
	 * @return
	 * @throws Exception
	 */
	int deleteMenu(MenuMngVO vo) throws Exception;	
	
}
