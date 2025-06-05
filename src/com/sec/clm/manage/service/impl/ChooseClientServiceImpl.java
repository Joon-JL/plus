/**
* Project Name : 계약관리시스템
* File Name :ChooseClientServiceImpl.java
* Description : 의뢰인선택팝업 Service Impl
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

import com.sec.clm.manage.dto.ChooseClientVO;
import com.sec.clm.manage.service.ChooseClientService;

public class ChooseClientServiceImpl extends CommonServiceImpl implements ChooseClientService {

	//2011.08.31 심주완추가 계약선택팝업에서 계약목록조회
	public List listChooseClient(ChooseClientVO vo) throws Exception {
		return commonDAO.list("clm.manage.listChooseClient", vo);		
	}
}