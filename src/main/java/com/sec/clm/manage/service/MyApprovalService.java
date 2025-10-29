/**
* Project Name : 계약관리시스템
* File Name :MyApprovalService.java
* Description : MyApprovalService인터페이스
* Author : 심주완
* Date : 2011.10.03
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.service;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.clm.manage.dto.MyApprovalVO;

public interface MyApprovalService {
	//MyApproval목록
	List listMyApproval(MyApprovalVO vo) throws Exception;
	
	//이력목록
	List listMyApprovalHis(MyApprovalVO vo) throws Exception;
	
	//MyApproval등록
	int modifyMyApproval(MyApprovalVO vo) throws Exception;
	
	//MyApproval 계약상세
	List detailMyApprovalContractMaster(MyApprovalVO vo) throws Exception;
	
	//MyApproval 계약상세
	List detailMyApproval(MyApprovalVO vo) throws Exception;
	
	//관리이력 삽입 (계약정보수정)
	int insertOrgMngHis(MyApprovalVO vo) throws Exception;
	
	//관리이력 max seqno 구하기
	int getOrgMngHisMaxSeqNo(MyApprovalVO vo) throws Exception;
	
	//관리이력 삽입 (원본출납관리)
	int insertOrgMngHis2(MyApprovalVO vo) throws Exception;
	
	//표지인쇄 
	ListOrderedMap printMyApproval(MyApprovalVO vo) throws Exception;
}