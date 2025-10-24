package com.sds.secframework.menu.service.impl;

import java.util.List;
import java.util.Map;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.menu.dto.MenuVO;
import com.sds.secframework.menu.service.MenuService;

public class MenuServiceImpl extends CommonServiceImpl implements MenuService {

	/* (non-Javadoc)
	 * @see com.sds.secframework.menu.service.MenuService#listTreeMenuUserAuth(com.sds.secframework.menu.dto.MenuVO)
	 */
	public List listTreeMenuUserAuth(MenuVO vo) throws Exception {
		return commonDAO.list("secfw.menu.listTreeMenuUserAuth", vo) ;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sds.secframework.menu.service.MenuService#getMenuDetail(com.sds.secframework.menu.dto.MenuVO)
	 */
	public Map getMenuDetail(MenuVO vo) throws Exception {
		Map map = null ;
		
		List list = commonDAO.list("secfw.menu.detail", vo) ;
		if(list!=null && list.size()>0) {
			map = (Map)list.get(0) ;
		}
		
		return map;
	}
	
	

}
