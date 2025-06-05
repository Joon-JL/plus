/**
* Project Name : 계약관리시스템
* File Name : ManageService.java
* Description : 계약공통 목록 Service
* Author : 신남원
* Date : 2010.09.29
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.manage.service;

import java.util.HashMap;
import java.util.List;

import com.sec.clm.manage.dto.ConclusionVO;
import com.sec.clm.manage.dto.ManageVO;

public interface ManageService {

	/**
	* 계약공통 목록조회(계약검토~종료)
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	List listManage(ManageVO vo) throws Exception;

	/**
	* 계약공통 목록조회 Legal Admin(계약검토~종료)
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	List listManage_legalAdmin(ManageVO vo) throws Exception;

	
	/**
	* MyContract 목록조회
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	List listMyContract(ManageVO vo) throws Exception;
	
	/**
	* StatisticsListManagelistMyContract 목록조회
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	List StatisticsListManagelistMyContract(ManageVO vo) throws Exception;

	/**
	* MyApproval 목록조회
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	List listMyApproval(ManageVO vo) throws Exception;

	/**
	* 계약서담당자변경요청 목록조회
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	List listRequest(ManageVO vo) throws Exception;

	/**
	* 계약서담당자변경 목록조회
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	List listChange(ManageVO vo) throws Exception;

	/**
	* 타시스템에 제공되는 계약리스트
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	List listContract(ManageVO vo) throws Exception;
	
	/**
	* IRP시스템에 제공되는 계약리스트
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/	
	List listContractIRP(ManageVO vo) throws Exception;
	
	/**
	* 인터페이스 key_id로 계약id 가져오기
	* 
	* @param String
	* @return ConclusionVO
	* @throws Exception
	*/
	ConclusionVO getConclusionVObyKeyId(String KeyId,String sysNm) throws Exception;
	
	/**
	* 의뢰목록의 Layer에 출력되는 계약목록
	* 
	* @param HashMap
	* @return String
	* @throws Exception
	*/
	String getCntrtHTML(HashMap hm) throws Exception;
	
	/**
	* 유형페이지
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	List listType(ManageVO vo) throws Exception;
	
	/**
	 * 의뢰현황 엑셀다운 
	 * @return
	 */
	List listConsiderationExcel(ManageVO vo) throws Exception;

	/**
	* 기간연장 승인 목록조회
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	List listAutoRenewApproval(ManageVO vo) throws Exception;
	
	/**
	* 체결후등록 승인 목록조회
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	public List listRegistrationApproval(ManageVO vo) throws Exception;
	/**
	* 표준계약서 목록조회
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	List listStdContract(ManageVO vo) throws Exception;
	
	/**
	* 의뢰현황 (사업장 구분별)
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	List listConsiderationMnCnsdDept(ManageVO vo) throws Exception;
	
	/**
	* 의뢰현황 (사업별)
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	List listConsiderationMnCnsdOrgnz(ManageVO vo) throws Exception;

}