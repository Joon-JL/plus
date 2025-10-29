/**
* Project Name : 계약관리시스템
* File Name : NationServiceImpl.java
* Description : 국가검색 팝업 ServiceImpl
* Author : dawn
* Date : 2011.10.26
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.draft.service.impl;

import java.util.List;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.clm.draft.dto.NationVO;
import com.sec.clm.draft.service.NationService;

public class NationServiceImpl extends CommonServiceImpl implements NationService{

	
	/**
	* 국가검색
	* 
	* @param NationVO
	* @return List
	* @throws Exception
	*/
	public List listNation(NationVO vo) throws Exception {
		return commonDAO.list("clm.draft.nation", vo);
	}
}
