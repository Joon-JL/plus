package com.sds.secframework.menu.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.menu.dto.SiteMapVO;
import com.sds.secframework.menu.service.SiteMapService;

public class SiteMapServiceImpl extends CommonServiceImpl implements SiteMapService {
	
	/**
	 * ID 값을 생성하기 위한 idGeneration 선언 및 세팅
	 */
	private IIdGenerationService idGenerationService = null;
	public void setIdGenerationService(IIdGenerationService idGenerationService) {
		this.idGenerationService = idGenerationService;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap SerchAuth(SiteMapVO vo)  throws Exception{
		HashMap result = new HashMap(); 
		
		List resultList = commonDAO.list("secfw.menu.SiteList", vo);	
		String MenuId   = "";
		
		for (int i=1; i<=resultList.size(); i++) {
			ListOrderedMap lmsub = (ListOrderedMap)resultList.get(i-1);
			MenuId = (String)lmsub.get("menu_id");
			vo.setMenu_id(MenuId);
			result.put("Menu"+i, commonDAO.list("secfw.menu.SiteListDetail", vo));
		}
		
		result.put("resultList", resultList);
		
		return result;	
	}
}
