package com.sds.secframework.menu.service;

import java.util.List;
import java.util.Map;

import com.sds.secframework.menu.dto.MenuVO;

public interface MenuService {
	/**
	 * 사용자 권한이 있는 메뉴 트리 리스트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listTreeMenuUserAuth(MenuVO vo) throws Exception ;
	
	/**
	 * 메뉴 상세 정보를 가져온다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map getMenuDetail(MenuVO vo) throws Exception ;
}
