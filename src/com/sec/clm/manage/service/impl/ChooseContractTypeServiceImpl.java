/**
* Project Name : 계약관리시스템
* File Name :ChooseContractTypeServiceImpl.java
* Description : 계약유형선택팝업 Service Impl
* Author : 심주완
* Date : 2011.09.25
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.service.impl;


import java.util.List;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.StringUtil;

import com.sec.clm.manage.dto.ChooseContractTypeVO;
import com.sec.clm.manage.service.ChooseContractTypeService;

public class ChooseContractTypeServiceImpl extends CommonServiceImpl implements ChooseContractTypeService {

	//2011.08.31 심주완추가 계약선택팝업에서 계약목록조회
	public List listChooseContractType(ChooseContractTypeVO vo) throws Exception {
		return commonDAO.list("clm.manage.listChooseContractType", vo);		
	}
	
	//2011.09.02 심주완 추가-계약유형선택팝업에서 비지니스의 분류
	public List listChooseContractTypeBiz(ChooseContractTypeVO vo) throws Exception {
		return commonDAO.list("clm.manage.listChooseContractTypeBiz", vo);
	}
	
	//2011.09.02 심주완 추가-계약유형선택팝업에서 계약의 단계
	public List listChooseContractTypeDepth(ChooseContractTypeVO vo) throws Exception {
		return commonDAO.list("clm.manage.listChooseContractTypeDepth", vo);
	}
	
	//2011.09.02 심주완 추가-계약유형선택팝업에서 체결의 목적
	public List listChooseContractTypePurpose(ChooseContractTypeVO vo) throws Exception {
		return commonDAO.list("clm.manage.listChooseContractTypePurpose", vo);
	}
}