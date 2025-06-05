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

import com.sec.clm.manage.dto.ChooseContractVO;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.service.ChooseContractService;

public class ChooseContractServiceImpl extends CommonServiceImpl implements ChooseContractService {

	//2011.08.31 심주완추가 계약선택팝업에서 계약목록조회
	public List listChooseContract(ChooseContractVO vo) throws Exception {
		return commonDAO.list("clm.manage.listChooseContract", vo);		
	}
	
	public int modifyChooseContract(ChooseContractVO vo) throws Exception {
		int result = 0;
		for(int i=0; i < vo.getCntrt_id_arr().length; i++) {
			if("N".equals(vo.getApproval_yn_arr()[i])) {
				vo.setCntrt_id(vo.getCntrt_id_arr()[i]);
				result = commonDAO.insert("clm.manage.modifyChooseContract", vo);
			}	
		}
		return result;
	}
	
	/**
	 * 계약유효성조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listApprovalValidation(ChooseContractVO vo) throws Exception {
		return commonDAO.list("clm.manage.listApprovalValidation", vo);
	}
	
}