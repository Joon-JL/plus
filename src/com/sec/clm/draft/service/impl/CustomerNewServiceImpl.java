/**
* Project Name : 계약관리시스템
* File Name : CustomerNewServiceImpl.java
* Description : 계약상대방 신규등록 팝업 ServiceImpl
* Author : dawn
* Date : 2011.10.21
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.draft.service.impl;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.clm.draft.dto.CustomerNewVO;
import com.sec.clm.draft.dto.CustomerVO;
import com.sec.clm.draft.service.CustomerNewService;

public class CustomerNewServiceImpl extends CommonServiceImpl implements CustomerNewService{

	/**
	* 계약상대방 신규등록
	* 
	* @param CustomerNewVO
	* @return int
	* @throws Exception
	*/
	public int insertCustomer(CustomerNewVO vo) throws Exception {
		String customerCdSeq = "";
		String secCdSeq      = "";
		
		//업체코드 일련번호 구하기
		List customerCdList = commonDAO.list("clm.draft.customerCdSeq");
		if(customerCdList != null && customerCdList.size() > 0 ){
			ListOrderedMap customerCd = (ListOrderedMap)customerCdList.get(0);
			customerCdSeq = (String) customerCd.get("max_customer_cd");
		}
		
		//SEC_CD 구하기
		List secCdList = commonDAO.list("clm.draft.secCd", vo);
		if(secCdList != null && secCdList.size() > 0 ){
			ListOrderedMap secCd = (ListOrderedMap)secCdList.get(0);
			secCdSeq = (String) secCd.get("max_sec_cd");
		}
		
		vo.setSec_cd(secCdSeq);
		
		//코드값 셋팅
		vo.setCustomer_cd(customerCdSeq);//업체코드
		vo.setGudun(customerCdSeq);//Global Ultimate DUNS Number
		vo.setDodun(customerCdSeq);//Domestic N DUNS Number
		vo.setHqdun(customerCdSeq);//Headquater DUNS Number
		
		//코드명 셋팅
		vo.setIv_nm1(vo.getCustomer_nm1());//업체명(영문)
		vo.setGunam(vo.getCustomer_nm1());//Global Ultimate Name
		vo.setDonam(vo.getCustomer_nm1());//Domestic N Name
		vo.setHqnam(vo.getCustomer_nm1());//Headquater Name
		vo.setCustomer_sort(vo.getCustomer_nm1());//업체명(약식)
		
		//대표자명 셋팅
		vo.setCeonm_eng(vo.getRep_nm());//대표자명(영문)
		
		//주소 셋팅
		vo.setAdd_eng(vo.getStreet());//본사주소(영문)

		return commonDAO.insert("clm.draft.insertCustomer", vo);
	}
	
	/**
	* 거래상대방 중복 체크
	* 
	* @param CustomerVO
	* @return boolean
	* @throws Exception
	*/
	public List checkTexNo(CustomerNewVO vo) throws Exception {
		return commonDAO.list("clm.draft.checkTexNo", vo);
	}

	/**
	* 계약상대방 신규등록
	* 
	* @param CustomerNewVO
	* @return String
	* @throws Exception
	*/
	public String insertCustomerReturnCd(CustomerNewVO vo) throws Exception {
		String customerCdSeq = "";
		String secCdSeq      = "";
		
		//업체코드 일련번호 구하기
		List customerCdList = commonDAO.list("clm.draft.customerCdSeq");
		if(customerCdList != null && customerCdList.size() > 0 ){
			ListOrderedMap customerCd = (ListOrderedMap)customerCdList.get(0);
			customerCdSeq = (String) customerCd.get("max_customer_cd");
		}
		
		//SEC_CD 구하기
		List secCdList = commonDAO.list("clm.draft.secCd", vo);
		if(secCdList != null && secCdList.size() > 0 ){
			ListOrderedMap secCd = (ListOrderedMap)secCdList.get(0);
			secCdSeq = (String) secCd.get("max_sec_cd");
		}
		
		vo.setSec_cd(secCdSeq);
		
		//코드값 셋팅
		vo.setCustomer_cd(customerCdSeq);//업체코드
		vo.setGudun(customerCdSeq);//Global Ultimate DUNS Number
		vo.setDodun(customerCdSeq);//Domestic N DUNS Number
		vo.setHqdun(customerCdSeq);//Headquater DUNS Number
		
		//코드명 셋팅
		vo.setIv_nm1(vo.getCustomer_nm1());//업체명(영문)
		vo.setGunam(vo.getCustomer_nm1());//Global Ultimate Name
		vo.setDonam(vo.getCustomer_nm1());//Domestic N Name
		vo.setHqnam(vo.getCustomer_nm1());//Headquater Name
		vo.setCustomer_sort(vo.getCustomer_nm1());//업체명(약식)
		
		//대표자명 셋팅
		vo.setCeonm_eng(vo.getRep_nm());//대표자명(영문)
		
		//주소 셋팅
		vo.setAdd_eng(vo.getStreet());//본사주소(영문)

		commonDAO.insert("clm.draft.insertCustomer", vo);
		
		return customerCdSeq;
	}

//	@Override
//	public List checkGerpCdNewInsert(CustomerNewVO vo) throws Exception {
//		// TODO Auto-generated method stub
//		return commonDAO.list("clm.draft.checkGerpCdNewInsert", vo);
//	}

}
